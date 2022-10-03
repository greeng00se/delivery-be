package me.myeats.delivery.shop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionGroupSpecDto {

    private String name;

    private boolean exclusive;

    private boolean basic;

    private List<OptionSpecDto> options;

    @Builder
    public OptionGroupSpecDto(String name, boolean exclusive, boolean basic, List<OptionSpecDto> options) {
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.options = options;
    }
}
