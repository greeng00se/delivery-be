package me.myeats.delivery.shop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.money.Money;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopDto {

    private String name;

    private boolean open;

    private Money minOrderAmount;

    private String address;

    private String phoneNumber;

    @Builder
    public ShopDto(String name, boolean open, Money minOrderAmount, String address, String phoneNumber) {
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
