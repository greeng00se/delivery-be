package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OptionGroupSpecDto {

    private String name;

    private boolean exclusive;
    
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
