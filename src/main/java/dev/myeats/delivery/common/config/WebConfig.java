package dev.myeats.delivery.common.config;

import dev.myeats.delivery.common.money.LongToMoneyConverter;
import dev.myeats.delivery.common.money.MoneyToLongConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LongToMoneyConverter());
        registry.addConverter(new MoneyToLongConverter());
    }
}
