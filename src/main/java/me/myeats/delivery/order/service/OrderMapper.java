package me.myeats.delivery.order.service;

import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderLineItem;
import me.myeats.delivery.order.domain.OrderOption;
import me.myeats.delivery.order.domain.OrderOptionGroup;
import me.myeats.delivery.order.domain.OrderStatus;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.dto.CartItemDto;
import me.myeats.delivery.order.dto.CartOptionDto;
import me.myeats.delivery.order.dto.CartOptionGroupDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.toList;

@Component
public class OrderMapper {

    public Order toOrder(CartDto cart) {
        return Order.builder()
                .shopId(cart.getShopId())
                .userId(cart.getUserId())
                .orderedTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .orderLineItems(cart.getCartItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(toList()))
                .build();
    }

    public OrderLineItem toOrderLineItem(CartItemDto cartItem) {
        return OrderLineItem.builder()
                .menuId(cartItem.getMenuId())
                .name(cartItem.getName())
                .count(cartItem.getCount())
                .groups(cartItem.getGroups()
                        .stream()
                        .map(this::toOrderOptionGroup)
                        .collect(toList()))
                .build();
    }

    private OrderOptionGroup toOrderOptionGroup(CartOptionGroupDto cartOptionGroup) {
        return OrderOptionGroup.builder()
                .name(cartOptionGroup.getName())
                .orderOptions(cartOptionGroup.getOptions()
                        .stream()
                        .map(this::toOrderOption)
                        .collect(toList()))
                .build();
    }

    private OrderOption toOrderOption(CartOptionDto cartOption) {
        return OrderOption.builder()
                .name(cartOption.getName())
                .price(Money.wons(cartOption.getPrice()))
                .build();
    }

}
