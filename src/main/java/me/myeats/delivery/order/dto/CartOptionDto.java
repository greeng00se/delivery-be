package me.myeats.delivery.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOptionDto {

    private String name;
    private Long price;

    @Builder
    public CartOptionDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
