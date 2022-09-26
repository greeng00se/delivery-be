package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MenuSaveRequestDto {

    List<MenuDto> menus;

    @Builder
    public MenuSaveRequestDto(MenuDto... menus) {
        this.menus = List.of(menus);
    }
}
