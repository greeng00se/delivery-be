package me.myeats.delivery.shop.domain;

import me.myeats.delivery.test.fixture.MenuFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @BeforeEach
    void clean() {
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("ShopId로 모든 메뉴 조회")
    void findMenuListByShopId() {
        // given
        Long shopId = 1L;
        Menu menu = MenuFixtures.menu()
                .shopId(shopId)
                .build();
        menuRepository.save(menu);

        // when
        List<Menu> menus = menuRepository.findAllByShopId(shopId);

        // then
        assertThat(menus.size()).isEqualTo(1L);
        assertThat(menus).containsExactly(menu);
    }

    @Test
    @DisplayName("메뉴가 존재하지 않을경우 빈 리스트 반환")
    void findMenuListByShopIdWithNoMenus() {
        // expect
        List<Menu> menus = menuRepository.findAllByShopId(987654321L);
        assertThat(menus).isEmpty();
    }
}
