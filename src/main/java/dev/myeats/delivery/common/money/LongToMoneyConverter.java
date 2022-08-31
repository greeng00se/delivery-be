package dev.myeats.delivery.common.money;

import org.springframework.core.convert.converter.Converter;

public class LongToMoneyConverter implements Converter<Long, Money> {

    @Override
    public Money convert(Long source) {
        return Money.wons(source);
    }
}
