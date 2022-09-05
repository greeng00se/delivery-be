package me.myeats.delivery.common.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyConverterTest {

    @Test
    @DisplayName("데이터베이스 컨버터 Long -> Money")
    void longToMoneyDatabaseConverter() {
        // given
        MoneyConverter converter = new MoneyConverter();

        // when
        Money result = converter.convertToEntityAttribute(10000L);

        // then
        assertThat(result).isInstanceOf(Money.class);
    }

    @Test
    @DisplayName("데이터베이스 컨버터 Money -> Long")
    void moneyToLongDatabaseConverter() {
        // given
        MoneyConverter converter = new MoneyConverter();

        // when
        Long result = converter.convertToDatabaseColumn(Money.wons(10000L));

        // then
        assertThat(result).isInstanceOf(Long.class);
    }

}
