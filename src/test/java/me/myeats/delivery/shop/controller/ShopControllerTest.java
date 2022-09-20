package me.myeats.delivery.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveRequestDto;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import me.myeats.delivery.test.security.WithCustomOwner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    @DisplayName("상점 등록")
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
}
