package me.myeats.delivery.shop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuDto {

    private String name;

    private String description;

    private Long price;

    private Long priority;

    private List<OptionGroupSpecDto> optionGroups;

    @Builder
    public MenuDto(String name, String description, Long price, Long priority,
                   List<OptionGroupSpecDto> optionGroups) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.optionGroups = optionGroups;
    }
}
