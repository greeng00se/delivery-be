package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class MenuSaveRequestDto {

    List<MenuDto> menus;

    @Builder
    public MenuSaveRequestDto(MenuDto... menus) {
        this.menus = List.of(menus);
    }
}
