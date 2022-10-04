package me.myeats.delivery.shop.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.shop.dto.ShopDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopSearchResponseDto {

    private Integer size;
    private List<ShopDto> shops;

    @Builder
    public ShopSearchResponseDto(Integer size, List<ShopDto> shops) {
        this.size = size;
        this.shops = shops;
    }
}
