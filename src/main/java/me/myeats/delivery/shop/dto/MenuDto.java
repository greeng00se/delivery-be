package me.myeats.delivery.shop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
