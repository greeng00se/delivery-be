package me.myeats.delivery.order.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.order.InvalidOrderException;
import me.myeats.delivery.common.exception.shop.ShopNotFoundException;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ShopRepository shopRepository;

    public void validate(Order order) {
        validate(order, getShop(order));
    }

    private void validate(Order order, Shop shop) {
        if (!shop.isOpen()) {
            throw new InvalidOrderException("상점이 열려있지 않습니다.");
        }

        if (order.getOrderLineItems().isEmpty()) {
            throw new InvalidOrderException("주문 항목이 비어있습니다.");
        }

        if (!shop.isValidOrderAmount(order.calculateTotalPrice())) {
            throw new InvalidOrderException("최소 주문 금액 이상을 주문해주세요.");
        }
    }

    private Shop getShop(Order order) {
        return shopRepository.findById(order.getShopId()).orElseThrow(ShopNotFoundException::new);
    }
}
