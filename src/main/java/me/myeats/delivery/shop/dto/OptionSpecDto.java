package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
public class OptionSpecDto {

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @Builder
    public OptionSpecDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
