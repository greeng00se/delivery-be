package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopSearchResponseDto {

    private Integer size;
    private List<ShopDto> shopLists;

    @Builder
    public ShopSearchResponseDto(Integer size, List<ShopDto> shopLists) {
        this.size = size;
        this.shopLists = shopLists;
    }
}
