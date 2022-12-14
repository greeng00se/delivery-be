package me.myeats.delivery.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.request.ShopSaveRequestDto;
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
    @DisplayName("?????? ??????")
    @WithCustomOwner
    void save() throws Exception {
        // given
        ShopSaveRequestDto request = ShopSaveRequestDto.builder()
                .name("???????????????")
                .address("????????? ????????? ????????? 4000-1")
                .minOrderAmount(20000L)
                .phoneNumber("010-1234-5678")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        Shop shop = shopRepository.findAll().get(0);

        // then
        assertThat(shopRepository.count()).isEqualTo(1L);
        assertThat(shop.getName()).isEqualTo("???????????????");
        assertThat(shop.getAddress()).isEqualTo("????????? ????????? ????????? 4000-1");
        assertThat(shop.getMinOrderAmount()).isEqualTo(Money.wons(20000));
        assertThat(shop.getPhoneNumber()).isEqualTo("010-1234-5678");
    }

    @Test
    @DisplayName("?????? ??????")
    @WithCustomOwner
    void search() throws Exception {
        // given
        Long ownerId = ownerRepository.findAll().get(0).getId();

        List<Shop> shopList = IntStream.range(0, 3)
                .mapToObj(i -> ShopFixtures.shop()
                        .ownerId(ownerId)
                        .name("??????????????? " + i)
                        .build())
                .collect(Collectors.toList());
        shopRepository.saveAll(shopList);

        // expect
        mockMvc.perform(get("/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", Matchers.is(3)))
                .andExpect(jsonPath("$.shops[0].name").value("??????????????? 0"))
                .andExpect(jsonPath("$.shops[2].name").value("??????????????? 2"))
                .andDo(print());
    }
}
