package me.myeats.delivery.order.domain;

import me.myeats.delivery.test.fixture.OrderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    @DisplayName("결제 완료로 상태 변경")
    void paid() {
        // given
        Order order = OrderFixtures.order()
                .orderStatus(OrderStatus.ORDERED)
                .build();

        // when
        order.paid();

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PAYED);
    }

    @Test
    @DisplayName("배달 완료로 상태 변경")
    void delivered() {
        // given
        Order order = OrderFixtures.order()
                .orderStatus(OrderStatus.PAYED)
                .build();

        // when
        order.delivered();

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
    }
}
