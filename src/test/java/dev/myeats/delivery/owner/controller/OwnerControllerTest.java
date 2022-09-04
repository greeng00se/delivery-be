package dev.myeats.delivery.owner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.myeats.delivery.common.jwt.AuthRole;
import dev.myeats.delivery.common.jwt.Authority;
import dev.myeats.delivery.owner.domain.Owner;
import dev.myeats.delivery.owner.domain.OwnerRepository;
import dev.myeats.delivery.owner.dto.OwnerRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 - 정상")
    void register() throws Exception {
        // given
        OwnerRegisterDto.Request request = OwnerRegisterDto.Request.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

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
    @DisplayName("로그인 - 정상")
    void login() throws Exception {
        // given
        Owner owner = getOwner();
        Owner savedOwner = ownerRepository.save(owner);

        OwnerRegisterDto.Request request = OwnerRegisterDto.Request.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();
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

    private Owner getOwner() {
        Authority authority = Authority.builder()
                .authorityName(AuthRole.ROLE_OWNER)
                .build();

        Owner owner = Owner.builder()
                .name("green")
                .email("green@naver.com")
                .password(passwordEncoder.encode("goose"))
                .authorities(Collections.singleton(authority))
                .build();
        return owner;
    }
}
