package me.myeats.delivery.shop.dto;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class ShopSearchResponseDto {

    private Long count;
    private List<ShopDto> shopLists = new ArrayList<>();

    @Builder
    public ShopSearchResponseDto(Long count, List<ShopDto> shopLists) {
        this.count = count;
        this.shopLists = shopLists;
    }
}
