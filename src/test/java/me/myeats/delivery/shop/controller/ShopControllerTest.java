package me.myeats.delivery.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveRequestDto;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import me.myeats.delivery.test.fixture.ShopFixtures;
import me.myeats.delivery.test.security.WithCustomOwner;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeTransaction
    public void setup() {
        ownerRepository.deleteAll();
        shopRepository.deleteAll();
        ownerRepository.save(OwnerFixtures.owner().build());
    }

    @Test
    @DisplayName("가게 등록")
    @WithCustomOwner
    void save() throws Exception {
        // given
        ShopSaveRequestDto request = ShopSaveRequestDto.builder()
                .name("엽기떡볶이")
                .address("서울시 가나구 다라동 4000-1")
                .minOrderAmount(20000L)
                .phoneNumber("010-1234-5678")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/api/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(shopRepository.count()).isEqualTo(1L);
    }

    @Test
    @DisplayName("가게 조회")
    @WithCustomOwner
    void search() throws Exception {
        // given
        Long ownerId = ownerRepository.findAll().get(0).getId();

        List<Shop> shopList = IntStream.range(0, 3)
                .mapToObj(i -> ShopFixtures.shop()
                        .ownerId(ownerId)
                        .name("엽기떡볶이 " + i)
                        .build())
                .collect(Collectors.toList());
        shopRepository.saveAll(shopList);

        Shop otherShop = ShopFixtures.shop()
                .ownerId(ownerId + 1)
                .build();
        shopRepository.save(otherShop);

        // when
        mockMvc.perform(get("/api/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", Matchers.is(3)))
                .andExpect(jsonPath("$.shopLists[0].name").value("엽기떡볶이 0"))
                .andExpect(jsonPath("$.shopLists[2].name").value("엽기떡볶이 2"))
                .andDo(print());
    }
}
