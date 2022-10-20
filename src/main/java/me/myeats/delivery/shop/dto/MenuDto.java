package me.myeats.delivery.shop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuDto {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Long priority;

    private List<OptionGroupSpecDto> optionGroups;

    @Builder
    public MenuDto(Long id, String name, String description, Long price, Long priority, List<OptionGroupSpecDto> optionGroups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.optionGroups = optionGroups;
    }
}
