package me.myeats.delivery.unit.common.money

import me.myeats.delivery.common.money.Money
import me.myeats.delivery.common.money.MoneyConverter
import spock.lang.Specification

class MoneyConverterUnitTest extends Specification {

    MoneyConverter converter

    def setup() {
        converter = new MoneyConverter()
    }

    def "Database Column(Long) => Entity(Money)"() {
        given:
        long column = 1000L

        expect:
        converter.convertToEntityAttribute(column) == Money.wons(1000L)
    }

    def "Entity(Money) => Database Column(Long)"() {
        given:
        Money money = Money.wons(1000L)

        expect:
        converter.convertToDatabaseColumn(money) == 1000L
    }
}
