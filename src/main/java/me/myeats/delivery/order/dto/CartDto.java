package me.myeats.delivery.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartDto {

    private Long shopId;
    private Long userId;
    private List<CartItemDto> cartItems;

    @Builder
    public CartDto(Long shopId, Long userId, List<CartItemDto> cartItems) {
        this.shopId = shopId;
        this.userId = userId;
        this.cartItems = cartItems;
    }
}
