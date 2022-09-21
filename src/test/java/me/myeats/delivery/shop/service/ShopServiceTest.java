package me.myeats.delivery.shop.service;

import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveRequestDto;
import me.myeats.delivery.shop.dto.ShopSearchResponseDto;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import me.myeats.delivery.test.fixture.ShopFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    void save() throws Exception {
        // given
        Owner owner = OwnerFixtures.owner().build();

        ShopSaveRequestDto request = ShopSaveRequestDto.builder()
                .name("엽기떡볶이")
                .address("서울시 가나구 다라동 4000-1")
                .minOrderAmount(20000L)
                .phoneNumber("010-1234-5678")
                .build();

        // when
        shopService.save(request, owner);

        // then
        assertThat(shopRepository.count()).isEqualTo(1L);
    }

    @Test
    @DisplayName("상점 조회")
    void search() {
        // given
        Long ownerId = 1L;

        List<Shop> shopList = IntStream.range(0, 5)
                .mapToObj(i -> ShopFixtures.shop()
                        .ownerId(ownerId)
                        .name("엽기떡볶이 " + i)
                        .build())
                .collect(Collectors.toList());
        shopRepository.saveAll(shopList);

        // when
        ShopSearchResponseDto result = shopService.search(ownerId);

        // then
        assertThat(result.getSize()).isEqualTo(5);
    }
}
