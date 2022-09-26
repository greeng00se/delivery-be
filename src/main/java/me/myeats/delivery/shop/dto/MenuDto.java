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
public class MenuDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Long price;
    @NotNull
    private Long priority;

    private List<OptionGroupSpecDto> optionGroups;

    @Builder
    public MenuDto(String name, String description, Long price, Long priority,
                   OptionGroupSpecDto... optionGroups) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.optionGroups = List.of(optionGroups);
    }
}
