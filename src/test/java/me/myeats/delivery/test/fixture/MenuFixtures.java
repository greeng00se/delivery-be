package me.myeats.delivery.test.fixture;

import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.OptionGroupSpec;
import me.myeats.delivery.shop.domain.OptionSpec;

import java.util.List;

public class MenuFixtures {

    public static Menu.MenuBuilder menu() {
        return Menu.builder()
                .name("엽기메뉴")
                .description("분모자 떡볶이 선택 시, 떡이 분모자로 변경되어 제공됩니다.")
                .price(Money.wons(14000L))
                .priority(1L)
                .shopId(1L)
                .optionGroupSpecs(List.of(optionGroupSpec().build()));
    }

    private static OptionGroupSpec.OptionGroupSpecBuilder optionGroupSpec() {
        return OptionGroupSpec.builder()
                .name("메뉴선택")
                .exclusive(false)
                .basic(true)
                .optionSpecs(List.of(optionSpec().build()));
    }

    private static OptionSpec.OptionSpecBuilder optionSpec() {
        return OptionSpec.builder()
                .name("엽기떡볶이")
                .price(Money.wons(0L));
    }
}
