package me.myeats.delivery.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderRepository;
import me.myeats.delivery.order.domain.OrderStatus;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.dto.CartItemDto;
import me.myeats.delivery.order.dto.CartOptionDto;
import me.myeats.delivery.order.dto.CartOptionGroupDto;
import me.myeats.delivery.test.fixture.CustomerFixtures;
import me.myeats.delivery.test.fixture.OrderFixtures;
import me.myeats.delivery.test.security.WithCustomCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @BeforeTransaction
    public void setup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();

        customer = customerRepository.save(CustomerFixtures.customer().build());
    }

    @Test
    @DisplayName("주문")
    @WithCustomCustomer
    void order() throws Exception {
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
                .shopId(shopId)
                .userId(customer.getId())
                .cartItems(List.of(cartItemDto))
                .build();

        String json = objectMapper.writeValueAsString(cartDto);

        // when
        mockMvc.perform(post("/order/" + shopId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        // then
        Order savedOrder = orderRepository.findAll().get(0);
        assertThat(orderRepository.count()).isEqualTo(1L);
        assertThat(savedOrder.getOrderLineItems().size()).isEqualTo(1L);
        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDERED);
    }

    @Test
    @DisplayName("결제")
    void pay() throws Exception {
        // given
        Order order = OrderFixtures.order()
                .orderStatus(OrderStatus.ORDERED)
                .build();
        orderRepository.save(order);

        // when
        mockMvc.perform(post("/order/" + order.getId() + "/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PAYED);
    }

    @Test
    @DisplayName("배달")
    void delivery() throws Exception {
        // given
        Order order = OrderFixtures.order()
                .orderStatus(OrderStatus.PAYED)
                .build();
        orderRepository.save(order);

        // when
        mockMvc.perform(post("/order/" + order.getId() + "/delivery")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
    }
}
