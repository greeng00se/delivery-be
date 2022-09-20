package me.myeats.delivery.shop.dto;

import lombok.Builder;
import me.myeats.delivery.common.money.Money;

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
