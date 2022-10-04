package me.myeats.delivery.shop.service;

import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.OptionGroupSpec;
import me.myeats.delivery.shop.domain.OptionSpec;
import me.myeats.delivery.shop.dto.MenuDto;
import me.myeats.delivery.shop.dto.OptionGroupSpecDto;
import me.myeats.delivery.shop.dto.OptionSpecDto;
import me.myeats.delivery.test.fixture.MenuFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MenuMapperTest {

    private MenuMapper menuMapper = new MenuMapper();

    @Test
    @DisplayName("MenuDto -> Menu 변환")
    void toMenu() {
        // given
        Long shopId = 1L;
        OptionSpecDto option1 = OptionSpecDto.builder()
                .name("엽기떡볶이")
                .price(0L)
                .build();
        OptionSpecDto option2 = OptionSpecDto.builder()
                .name("엽기반반")
                .price(1000L)
                .build();

        OptionGroupSpecDto optionGroup = OptionGroupSpecDto.builder()
                .name("메뉴선택")
                .exclusive(false)
                .basic(true)
                .options(List.of(option1, option2))
                .build();

        MenuDto menuDto = MenuDto.builder()
                .name("엽기메뉴")
                .description("분모자 떡볶이 선택 시, 떡이 분모자로 변경되어 제공됩니다.")
                .price(14000L)
                .priority(1L)
                .optionGroups(List.of(optionGroup))
                .build();

        // when
        Menu menu = menuMapper.toMenu(menuDto, shopId);

        // then
        String[] exceptField = {"id", "createDate", "modifiedDate"};
        assertThat(menu).isInstanceOf(Menu.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

        OptionGroupSpec optionGroupSpec = menu.getOptionGroupSpecs().get(0);
        assertThat(optionGroupSpec).isInstanceOf(OptionGroupSpec.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);

        OptionSpec optionSpec = optionGroupSpec.getOptionSpecs().get(0);
        assertThat(optionSpec).isInstanceOf(OptionSpec.class)
                .hasNoNullFieldsOrPropertiesExcept(exceptField);
    }

    @Test
    @DisplayName("Menu -> MenuDto 변환")
    void toMenuDto() {
        // given
        Menu menu = MenuFixtures.menu().build();

        // when
        MenuDto menuDto = menuMapper.toMenuDto(menu);

        // then
        assertThat(menuDto).isInstanceOf(MenuDto.class)
                .hasNoNullFieldsOrProperties();

        OptionGroupSpecDto optionGroupSpecDto = menuDto.getOptionGroups().get(0);
        assertThat(optionGroupSpecDto).isInstanceOf(OptionGroupSpecDto.class)
                .hasNoNullFieldsOrProperties();

        OptionSpecDto optionSpecDto = optionGroupSpecDto.getOptions().get(0);
        assertThat(optionSpecDto).isInstanceOf(OptionSpecDto.class)
                .hasNoNullFieldsOrProperties();
    }

}
