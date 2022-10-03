package me.myeats.delivery.shop.service;

import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.MenuRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.MenuDto;
import me.myeats.delivery.shop.dto.OptionGroupSpecDto;
import me.myeats.delivery.shop.dto.OptionSpecDto;
import me.myeats.delivery.shop.dto.request.MenuSaveRequestDto;
import me.myeats.delivery.test.fixture.ShopFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class MenuServiceTest {
    private static final Long OWNER_ID = 1L;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuService menuService;

    @BeforeEach
    void clean() {
        menuRepository.deleteAll();
        shopRepository.deleteAll();
        Shop shop = ShopFixtures.shop().ownerId(OWNER_ID).build();
        shopRepository.save(shop);
    }

    @Test
    @DisplayName("메뉴 등록")
    void save() {
        // given
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

        MenuDto menu = MenuDto.builder()
                .name("엽기메뉴")
                .description("분모자 떡볶이 선택 시, 떡이 분모자로 변경되어 제공됩니다.")
                .price(14000L)
                .priority(1L)
                .optionGroups(List.of(optionGroup))
                .build();

        MenuSaveRequestDto request = new MenuSaveRequestDto(List.of(menu));

        Shop shop = shopRepository.findAll().get(0);

        // when
        menuService.save(shop.getId(), request, OWNER_ID);

        // then
        Menu savedMenu = menuRepository.findAll().get(0);
        assertThat(menuRepository.count()).isEqualTo(1L);
        assertThat(savedMenu.getOptionGroupSpecs().size()).isEqualTo(1L);
    }

    @Test
    @DisplayName("메뉴 등록시 해당 상점에 대한 권한 없는 경우")
    void saveWithUnauthorized() {
        // given
        MenuSaveRequestDto request = new MenuSaveRequestDto(List.of());
        Shop shop = shopRepository.findAll().get(0);

        // when
        assertThatThrownBy(() -> menuService.save(shop.getId(), request, OWNER_ID + 99999))
                .isInstanceOf(UnauthorizedException.class);
    }
}
