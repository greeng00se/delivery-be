package me.myeats.delivery.order.service;

import me.myeats.delivery.common.exception.order.InvalidOrderException;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.test.fixture.OrderFixtures;
import me.myeats.delivery.test.fixture.ShopFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class OrderValidatorTest {

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("주문 검증 성공")
    void validate() {
        // given
        Shop shop = ShopFixtures.shop()
                .open(true)
                .minOrderAmount(Money.wons(10000L))
                .build();
        shopRepository.save(shop);

        Order order = OrderFixtures.order()
                .shopId(shop.getId())
                .build();

        // expect
        orderValidator.validate(order);
    }

    @Test
    @DisplayName("상점이 닫힌 경우 InvalidOrderException 발생")
    void validateWithShopNotOpened() {
        // given
        Shop shop = ShopFixtures.shop()
                .open(false)
                .minOrderAmount(Money.wons(10000L))
                .build();
        shopRepository.save(shop);

        Order order = OrderFixtures.order()
                .shopId(shop.getId())
                .build();

        // expect
        assertThatThrownBy(() -> orderValidator.validate(order))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("주문 항목이 비어있는 경우 InvalidOrderException 발생")
    void validateWithNoOrder() {
        // given
        Shop shop = ShopFixtures.shop()
                .open(true)
                .minOrderAmount(Money.wons(10000L))
                .build();
        shopRepository.save(shop);

        Order order = OrderFixtures.order()
                .shopId(shop.getId())
                .orderLineItems(List.of())
                .build();

        // expect
        assertThatThrownBy(() -> orderValidator.validate(order))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("최소 주문 금액을 못넘긴 경우 InvalidOrderException 발생")
    void validateWithLowPrice() {
        // given
        Shop shop = ShopFixtures.shop()
                .open(true)
                .minOrderAmount(Money.wons(200000L))
                .build();
        shopRepository.save(shop);

        Order order = OrderFixtures.order()
                .shopId(shop.getId())
                .build();

        // expect
        assertThatThrownBy(() -> orderValidator.validate(order))
                .isInstanceOf(InvalidOrderException.class);
    }
}
