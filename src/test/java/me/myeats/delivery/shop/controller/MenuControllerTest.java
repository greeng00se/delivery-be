package me.myeats.delivery.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.MenuRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.MenuDto;
import me.myeats.delivery.shop.dto.OptionGroupSpecDto;
import me.myeats.delivery.shop.dto.OptionSpecDto;
import me.myeats.delivery.shop.dto.request.MenuSaveRequestDto;
import me.myeats.delivery.test.fixture.MenuFixtures;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeTransaction
    public void setup() {
        ownerRepository.deleteAll();
        shopRepository.deleteAll();
        menuRepository.deleteAll();
        Owner owner = ownerRepository.save(OwnerFixtures.owner().build());
        Shop shop = ShopFixtures.shop()
                .ownerId(owner.getId())
                .build();
        shopRepository.save(shop);
    }

    @Test
    @DisplayName("메뉴 등록")
    @WithCustomOwner
    void save() throws Exception {
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
        String json = objectMapper.writeValueAsString(request);

        Shop shop = shopRepository.findAll().get(0);

        // when
        mockMvc.perform(post("/shop/" + shop.getId() + "/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        // then
        Menu savedMenu = menuRepository.findAll().get(0);
        assertThat(menuRepository.count()).isEqualTo(1L);
        assertThat(savedMenu.getOptionGroupSpecs().size()).isEqualTo(1L);
    }

    @Test
    @DisplayName("메뉴 조회")
    @WithCustomOwner
    void search() throws Exception {
        // given
        Long shopId = 1L;
        Menu menu = MenuFixtures.menu()
                .name("엽기 메뉴")
                .shopId(shopId)
                .build();
        Menu savedMenu = menuRepository.save(menu);

        // expect
        mockMvc.perform(get("/shop/" + shopId + "/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", Matchers.is(1)))
                .andExpect(jsonPath("$.menus[0].name").value(savedMenu.getName()))
                .andExpect(jsonPath("$.menus[0].optionGroups").exists())
                .andExpect(jsonPath("$.menus[0].optionGroups[0].options").exists())
                .andDo(print());
    }
}
