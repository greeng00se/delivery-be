package dev.myeats.delivery.common.money;

import org.springframework.core.convert.converter.Converter;

public class MoneyToLongConverter implements Converter<Money, Long> {

    @Override
    public Long convert(Money source) {
        return source.getAmount().longValue();
    }
}
