package me.myeats.delivery.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemDto {

    private Long menuId;
    private String name;
    private int count;
    private List<CartOptionGroupDto> groups;

    @Builder
    public CartItemDto(Long menuId, String name, int count, List<CartOptionGroupDto> groups) {
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups = groups;
    }
}
