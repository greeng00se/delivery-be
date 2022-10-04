package me.myeats.delivery.shop.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.shop.dto.MenuDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSearchResponseDto {

    private Integer size;
    private List<MenuDto> menus;

    @Builder
    public MenuSearchResponseDto(Integer size, List<MenuDto> menus) {
        this.size = size;
        this.menus = menus;
    }
}
