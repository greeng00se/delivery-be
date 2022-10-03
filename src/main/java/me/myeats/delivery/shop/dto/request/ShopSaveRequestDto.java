package me.myeats.delivery.shop.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopSaveRequestDto {

    @NotBlank
    private String name;

    @NotNull
    private Long minOrderAmount;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @Builder
    public ShopSaveRequestDto(String name, Long minOrderAmount, String address, String phoneNumber) {
        this.name = name;
        this.minOrderAmount = minOrderAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
