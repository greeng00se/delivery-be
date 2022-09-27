package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OptionSpecDto {

    private String name;

    private Long price;

    @Builder
    public OptionSpecDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
