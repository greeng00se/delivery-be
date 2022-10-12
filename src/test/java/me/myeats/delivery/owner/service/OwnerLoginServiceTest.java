package me.myeats.delivery.owner.service;

import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.request.OwnerLoginRequestDto;
import me.myeats.delivery.owner.dto.response.OwnerLoginResponseDto;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class OwnerLoginServiceTest {

    @Autowired
    private OwnerLoginService ownerLoginService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        OwnerLoginRequestDto request = OwnerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();

        // when
        OwnerLoginResponseDto response = ownerLoginService.login(request);

        // then
        assertThat(tokenProvider.validateToken(response.getToken())).isTrue();
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFail() {
        // given
        OwnerLoginRequestDto request = OwnerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();

        // expect
        assertThatThrownBy(() -> ownerLoginService.login(request))
                .isInstanceOf(UnauthorizedException.class);
    }
}
