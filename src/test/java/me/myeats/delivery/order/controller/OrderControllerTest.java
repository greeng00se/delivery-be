package me.myeats.delivery.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderRepository;
import me.myeats.delivery.order.domain.OrderStatus;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.dto.CartItemDto;
import me.myeats.delivery.order.dto.CartOptionDto;
import me.myeats.delivery.order.dto.CartOptionGroupDto;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.test.fixture.CustomerFixtures;
import me.myeats.delivery.test.fixture.OrderFixtures;
import me.myeats.delivery.test.fixture.ShopFixtures;
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
    private ShopRepository shopRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @BeforeTransaction
    public void setup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        shopRepository.deleteAll();

        customer = customerRepository.save(CustomerFixtures.customer().build());
    }

    @Test
    @DisplayName("??????")
    @WithCustomCustomer
    void order() throws Exception {
        // given
        Shop shop = ShopFixtures.shop()
                .open(true)
                .minOrderAmount(Money.wons(14000L))
                .build();
        shopRepository.save(shop);

        CartOptionDto cartOptionDto = CartOptionDto.builder()
                .name("???????????????")
                .price(14000L)
                .build();

        CartOptionGroupDto cartOptionGroupDto = CartOptionGroupDto.builder()
                .name("????????????")
                .options(List.of(cartOptionDto))
                .build();

        CartItemDto cartItemDto = CartItemDto.builder()
                .name("????????????")
                .menuId(1L)
                .count(2)
                .groups(List.of(cartOptionGroupDto))
                .build();

        CartDto cartDto = CartDto.builder()
                .shopId(shop.getId())
                .userId(customer.getId())
                .cartItems(List.of(cartItemDto))
                .build();

        String json = objectMapper.writeValueAsString(cartDto);

        // when
        mockMvc.perform(post("/order/" + shop.getId())
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
    @DisplayName("??????")
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
    @DisplayName("??????")
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
