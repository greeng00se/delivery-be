package dev.myeats.delivery.owner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.myeats.delivery.fixture.OwnerFixtures;
import dev.myeats.delivery.owner.domain.Owner;
import dev.myeats.delivery.owner.domain.OwnerRepository;
import dev.myeats.delivery.owner.dto.OwnerLoginDto;
import dev.myeats.delivery.owner.dto.OwnerRegisterDto;
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
        OwnerRegisterDto.Request request = OwnerFixtures.registerRequest().build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/api/owner/register")
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

        OwnerLoginDto.Request request = OwnerFixtures.loginRequest().build();
        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/api/owner/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andDo(print());
    }
}
