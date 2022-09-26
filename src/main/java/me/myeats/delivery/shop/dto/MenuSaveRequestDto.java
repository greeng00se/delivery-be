package me.myeats.delivery.shop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MenuSaveRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Long price;

    @NotNull
    private Long priority;
}
