package me.myeats.delivery.shop.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.shop.dto.MenuDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSaveRequestDto {

    List<MenuDto> menus;

    @Builder
    public MenuSaveRequestDto(List<MenuDto> menus) {
        this.menus = menus;
    }
}
