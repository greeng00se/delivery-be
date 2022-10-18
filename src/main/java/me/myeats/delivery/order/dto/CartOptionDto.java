package me.myeats.delivery.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.money.Money;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOptionDto {
    
    private String name;
    private Money price;

    public CartOptionDto(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
