package me.myeats.delivery.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOptionGroupDto {

    private String name;
    private List<CartOptionDto> options;

    @Builder
    public CartOptionGroupDto(String name, List<CartOptionDto> options) {
        this.name = name;
        this.options = options;
    }
}
