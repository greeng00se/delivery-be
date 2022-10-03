package me.myeats.delivery.owner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.request.OwnerLoginRequestDto;
import me.myeats.delivery.owner.dto.request.OwnerRegisterRequestDto;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void register() throws Exception {
        // given
        OwnerRegisterRequestDto request = OwnerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/owner/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(ownerRepository.count()).isEqualTo(1L);

        Owner owner = ownerRepository.findAll().get(0);
        assertThat(owner.getName()).isEqualTo("green");
        assertThat(owner.getEmail()).isEqualTo("green@naver.com");
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        OwnerLoginRequestDto request = OwnerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();
        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/owner/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andDo(print());
    }
}
