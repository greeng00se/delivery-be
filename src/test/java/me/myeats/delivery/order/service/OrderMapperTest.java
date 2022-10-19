package me.myeats.delivery.order.service;

import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderLineItem;
import me.myeats.delivery.order.domain.OrderOption;
import me.myeats.delivery.order.domain.OrderOptionGroup;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.dto.CartItemDto;
import me.myeats.delivery.order.dto.CartOptionDto;
import me.myeats.delivery.order.dto.CartOptionGroupDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    private OrderMapper orderMapper = new OrderMapper();

    @Test
    @DisplayName("CartDto -> Order 변환")
    void toMenu() {
        // given
        Long shopId = 1L;
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
        Order order = orderMapper.toOrder(cartDto);

        // then
        String[] exceptField = {"id", "createDate", "modifiedDate"};
        assertThat(order).isInstanceOf(Order.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

        OrderLineItem orderLineItem = order.getOrderLineItems().get(0);
        assertThat(orderLineItem).isInstanceOf(OrderLineItem.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

        OrderOptionGroup orderOptionGroup = orderLineItem.getGroups().get(0);
        assertThat(orderOptionGroup).isInstanceOf(OrderOptionGroup.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

        OrderOption orderOption = orderOptionGroup.getOrderOptions().get(0);
        assertThat(orderOption).isInstanceOf(OrderOption.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

    }
}
