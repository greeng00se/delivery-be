package me.myeats.delivery.common.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("10000원에서 2000원을 더하면 12000원이다.")
    void plus() {
        // given
        Money money = Money.wons(10000);

        // when
        Money result = money.plus(Money.wons(2000));

        // then
        assertThat(result).isEqualTo(Money.wons(12000));
    }

    @Test
    @DisplayName("10000원에서 2000원을 빼면 8000원이다.")
    void minus() {
        // given
        Money money = Money.wons(10000);

        // when
        Money result = money.minus(Money.wons(2000));

        // then
        assertThat(result).isEqualTo(Money.wons(8000));
    }

    @Test
    @DisplayName("10000원의 10%는 1000원이다.")
    void multiply() {
        // given
        Money money = Money.wons(10000);
        double ratio = 0.1;

        // when
        Money result = money.times(ratio);

        // then
        assertThat(result).isEqualTo(Money.wons(1000));
    }

    @Test
    @DisplayName("Money.ZERO는 0원을 의미한다.")
    void zero() {
        // given
        Money zero = Money.ZERO;

        // expect
        assertThat(zero).isEqualTo(Money.wons(0));
    }

    @Test
    @DisplayName("getAmount 메서드는 BigDecimal 타입의 값을 반환한다.")
    void getAmount() {
        // given
        Money money = Money.wons(10000);

        // when
        BigDecimal result = money.getAmount();

        // then
        assertThat(result).isEqualTo(BigDecimal.valueOf(10000L));
    }

    @ParameterizedTest(name = "{0}원은 10000원 이상이다 = {1}")
    @CsvSource(value = {"10000, true", "10001, true", "9999, false"})
    void isGreaterThanOrEqualTest(Long amount, boolean returnValue) {
        // given
        Money minOrderAmount = Money.wons(10000);
        Money orderAmount = Money.wons(amount);

        // when
        boolean result = orderAmount.isGreaterThanOrEqual(minOrderAmount);

        // then
        assertThat(result).isEqualTo(returnValue);
    }
}
