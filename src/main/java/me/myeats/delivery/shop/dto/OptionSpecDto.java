package me.myeats.delivery.shop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionSpecDto {

    private String name;

    private Long price;

    @Builder
    public OptionSpecDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
