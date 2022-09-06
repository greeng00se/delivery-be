package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShopSaveDto {

    @Data
    public static class Request {

        @NotNull
        private Long minOrderAmount;

        @NotBlank
        private String address;

        @NotBlank
        private String phoneNumber;

        @Builder
        public Request(Long minOrderAmount, String address, String phoneNumber) {
            this.minOrderAmount = minOrderAmount;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }
    }
}
