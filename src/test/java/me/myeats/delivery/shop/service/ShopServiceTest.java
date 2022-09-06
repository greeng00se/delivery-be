package me.myeats.delivery.shop.service;

import me.myeats.delivery.fixture.OwnerFixtures;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShopServiceTest {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ShopService shopService;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
        ownerRepository.save(OwnerFixtures.owner().build());
        shopRepository.deleteAll();
    }

    @Test
    @DisplayName("상점 등록")
    @WithMockUser(username = "green", roles = {"OWNER"})
    void save() throws Exception {
        // given
        ShopSaveDto.Request request = ShopSaveDto.Request.builder()
                .address("서울시 가나구 다라동 4000-1")
                .minOrderAmount(20000L)
                .phoneNumber("010-1234-5678")
                .build();

        // when
        shopService.save(request);

        // then
        assertThat(shopRepository.count()).isEqualTo(1L);
    }

}
