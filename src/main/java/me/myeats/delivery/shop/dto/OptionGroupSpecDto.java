package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class OptionGroupSpecDto {

    @NotBlank
    private String name;

    @NotNull
    private boolean exclusive;

    @NotNull
    private boolean basic;

    private List<OptionSpecDto> options;

    @Builder
    public OptionGroupSpecDto(String name, boolean exclusive, boolean basic, OptionSpecDto... options) {
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.options = List.of(options);
    }
}
