package me.myeats.delivery.common.money

import spock.lang.Specification

class MoneyUnitTest extends Specification {

    def "금액의 합을 검증한다."() {
        given:
        Money moneyA = Money.wons(amountA)
        Money moneyB = Money.wons(amountB)

        expect:
        moneyA.plus(moneyB) == Money.wons(result)

        where:
        amountA | amountB | result
        10000L  | 20000L  | 30000L
        0L      | 300L    | 300L
        400L    | 0L      | 400L
    }

    def "금액의 차를 검증한다."() {
        given:
        Money moneyA = Money.wons(amountA)
        Money moneyB = Money.wons(amountB)

        expect:
        moneyA.minus(moneyB) == Money.wons(result)

        where:
        amountA | amountB | result
        20000L  | 10000L  | 10000L
        8000L   | 2000L   | 6000L
        20000L  | 0L      | 20000L
    }

    def "금액의 곱을 검증한다."() {
        given:
        Money money = Money.wons(amount)

        expect:
        money.times(ratio as double) == Money.wons(result)

        where:
        amount | ratio | result
        20000L | 0.1   | 2000L
        10000L | 0.15  | 1500L
        10000L | 1.5   | 15000L
    }

    def "Money.ZERO는 0원을 의미한다."() {
        expect:
        Money.ZERO == Money.wons(0)
    }

    def "getAmount 메서드는 BigDecimal 타입의 값을 반환한다."() {
        given:
        Money money = Money.wons(1000L)

        expect:
        money.getAmount() == BigDecimal.valueOf(1000L)
    }

    def "isGreaterThanOrEqual 메서드는 비교 금액 보다 이상의 경우 true, 미만의 경우 false를 반환한다."() {
        given:
        Money moneyA = Money.wons(amountA)
        Money moneyB = Money.wons(amountB)

        expect:
        moneyA.isGreaterThanOrEqual(moneyB) == result

        where:
        amountA | amountB | result
        20000L  | 19999L  | true
        20000L  | 20000L  | true
        20000L  | 20001L  | false
    }

    def "toString 메서드는 XXX원의 형식으로 값을 반환한다."() {
        given:
        Money money = Money.wons(amount)

        expect:
        money.toString() == result

        where:
        amount | result
        20000L | "20000원"
        10000L | "10000원"
        1000L  | "1000원"
    }
}
