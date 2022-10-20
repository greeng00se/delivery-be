package me.myeats.delivery.shop.service;

import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.OptionGroupSpec;
import me.myeats.delivery.shop.domain.OptionSpec;
import me.myeats.delivery.shop.dto.MenuDto;
import me.myeats.delivery.shop.dto.OptionGroupSpecDto;
import me.myeats.delivery.shop.dto.OptionSpecDto;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class MenuMapper {

    public Menu toMenu(MenuDto menuDto, Long shopId) {
        return Menu.builder()
                .name(menuDto.getName())
                .description(menuDto.getDescription())
                .price(Money.wons(menuDto.getPrice()))
                .priority(menuDto.getPriority())
                .shopId(shopId)
                .optionGroupSpecs(menuDto.getOptionGroups()
                        .stream()
                        .map(this::toOptionGroupSpec)
                        .collect(toList()))
                .build();
    }

    private OptionGroupSpec toOptionGroupSpec(OptionGroupSpecDto optionGroupSpecDto) {
        return OptionGroupSpec.builder()
                .name(optionGroupSpecDto.getName())
                .exclusive(optionGroupSpecDto.isExclusive())
                .basic(optionGroupSpecDto.isBasic())
                .optionSpecs(optionGroupSpecDto.getOptions()
                        .stream()
                        .map(this::toOptionSpec)
                        .collect(toList()))
                .build();
    }

    private OptionSpec toOptionSpec(OptionSpecDto optionSpecDto) {
        return OptionSpec.builder()
                .name(optionSpecDto.getName())
                .price(Money.wons(optionSpecDto.getPrice()))
                .build();
    }

    public MenuDto toMenuDto(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice().getAmount().longValue())
                .priority(menu.getPriority())
                .optionGroups(menu.getOptionGroupSpecs()
                        .stream()
                        .map(this::toOptionGroupSpecDto)
                        .collect(toList()))
                .build();
    }

    private OptionGroupSpecDto toOptionGroupSpecDto(OptionGroupSpec optionGroupSpec) {
        return OptionGroupSpecDto.builder()
                .name(optionGroupSpec.getName())
                .exclusive(optionGroupSpec.isExclusive())
                .basic(optionGroupSpec.isBasic())
                .options(optionGroupSpec.getOptionSpecs()
                        .stream()
                        .map(this::toOptionSpecDto)
                        .collect(toList()))
                .build();
    }

    private OptionSpecDto toOptionSpecDto(OptionSpec optionSpec) {
        return OptionSpecDto.builder()
                .name(optionSpec.getName())
                .price(optionSpec.getPrice().getAmount().longValue())
                .build();
    }

}
