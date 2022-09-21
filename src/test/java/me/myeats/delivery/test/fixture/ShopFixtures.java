package me.myeats.delivery.test.fixture;

import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.shop.domain.Shop;

public class ShopFixtures {

    public static Shop.ShopBuilder shop() {
        return Shop.builder()
                .ownerId(1L)
                .name("엽기떡볶이")
                .address("서울시 가나구 다라동 4000-1")
                .minOrderAmount(Money.wons(20000))
                .phoneNumber("010-1234-5678")
                .open(true);
    }
}
