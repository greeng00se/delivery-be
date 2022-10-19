package me.myeats.delivery.order.service;

import me.myeats.delivery.common.exception.order.InvalidOrderException;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderRepository;
import me.myeats.delivery.order.domain.OrderStatus;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.dto.CartItemDto;
import me.myeats.delivery.order.dto.CartOptionDto;
import me.myeats.delivery.order.dto.CartOptionGroupDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("정상적인 주문")
    void order() {
        // given
        CartOptionDto cartOptionDto = CartOptionDto.builder()
                .name("엽기떡볶이")
                .price(14000L)
                .build();

        CartOptionGroupDto cartOptionGroupDto = CartOptionGroupDto.builder()
                .name("메뉴선택")
                .options(List.of(cartOptionDto))
                .build();

        CartItemDto cartItemDto = CartItemDto.builder()
                .name("엽기메뉴")
                .menuId(1L)
                .count(2)
                .groups(List.of(cartOptionGroupDto))
                .build();

        CartDto cartDto = CartDto.builder()
                .shopId(1L)
                .userId(1L)
                .cartItems(List.of(cartItemDto))
                .build();

        // when
        orderService.order(cartDto, cartDto.getShopId(), cartDto.getUserId());

        // then
        Order savedOrder = orderRepository.findAll().get(0);
        assertThat(orderRepository.count()).isEqualTo(1L);
        assertThat(savedOrder.getOrderLineItems().size()).isEqualTo(1L);
        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDERED);
    }

    @Test
    @DisplayName("주문 정보와 고객 정보가 일치하지 않는 경우")
    void orderWithOtherCustomer() {
        // given
        CartDto cartDto = CartDto.builder()
                .shopId(1L)
                .userId(1L)
                .cartItems(List.of())
                .build();

        // expect
        assertThatThrownBy(() -> orderService.order(cartDto, 1L, 9999L))
                .isInstanceOf(InvalidOrderException.class);
    }
}
