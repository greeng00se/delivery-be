package me.myeats.delivery.test.fixture;

import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderLineItem;
import me.myeats.delivery.order.domain.OrderOption;
import me.myeats.delivery.order.domain.OrderOptionGroup;
import me.myeats.delivery.order.domain.OrderStatus;

import java.util.List;

public class OrderFixtures {

    public static Order.OrderBuilder order() {
        return Order.builder()
                .orderStatus(OrderStatus.ORDERED)
                .userId(1L)
                .shopId(1L)
                .orderLineItems(List.of(orderLineItem().build()));
    }

    public static OrderLineItem.OrderLineItemBuilder orderLineItem() {
        return OrderLineItem.builder()
                .name("엽기메뉴")
                .menuId(1L)
                .count(1)
                .groups(List.of(orderOptionGroup().build()));
    }

    private static OrderOptionGroup.OrderOptionGroupBuilder orderOptionGroup() {
        return OrderOptionGroup.builder()
                .name("메뉴선택")
                .orderOptions(List.of(orderOption().build()));
    }

    private static OrderOption.OrderOptionBuilder orderOption() {
        return OrderOption.builder()
                .name("엽기떡볶이")
                .price(Money.wons(14000L));
    }
}
