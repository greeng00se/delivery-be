package me.myeats.delivery.shop.domain;

import me.myeats.delivery.shop.dto.ShopDto;
import me.myeats.delivery.test.fixture.ShopFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShopRepositoryTest {

    @Autowired
    ShopRepository shopRepository;

    @Test
    @DisplayName("OwnerId로 모든 가게 조회")
    void findShopDtoListByOwnerId() {
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
        List<ShopDto> shops = shopRepository.findShopDtoListByOwnerId(ownerId);

        // then
        assertThat(shops.size()).isEqualTo(5L);
    }

    @Test
    @DisplayName("가게가 존재하지 않을경우 빈 리스트 반환")
    void findShopDtoListByOwnerIdWithNoShops() {
        // expect
        List<ShopDto> shops = shopRepository.findShopDtoListByOwnerId(987654321L);
        assertThat(shops).isEmpty();
    }
}
