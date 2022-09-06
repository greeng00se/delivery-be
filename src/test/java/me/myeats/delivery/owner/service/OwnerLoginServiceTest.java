package me.myeats.delivery.owner.service;

import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.fixture.OwnerFixtures;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.OwnerLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OwnerLoginServiceTest {

    @Autowired
    private OwnerLoginService ownerLoginService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        OwnerLoginDto.Request request = OwnerLoginDto.Request.builder()
                .name("green")
                .password("goose")
                .build();

        // when
        String token = ownerLoginService.login(request);
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFail() {
        // given
        OwnerLoginDto.Request request = OwnerLoginDto.Request.builder()
                .name("green")
                .password("goose")
                .build();

        // expect
        assertThatThrownBy(() -> ownerLoginService.login(request))
                .isInstanceOf(BadCredentialsException.class);
    }
}
