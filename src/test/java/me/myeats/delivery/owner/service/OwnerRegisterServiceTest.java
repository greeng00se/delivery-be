package me.myeats.delivery.owner.service;

import me.myeats.delivery.common.exception.owner.OwnerNameDuplicateException;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.request.OwnerRegisterRequestDto;
import me.myeats.delivery.owner.dto.response.OwnerRegisterResponseDto;
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
class OwnerRegisterServiceTest {

    @Autowired
    private OwnerRegisterService ownerRegisterService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        // given
        OwnerRegisterRequestDto request = OwnerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        // when
        OwnerRegisterResponseDto response = ownerRegisterService.register(request);
        Owner owner = ownerRepository.findAll().get(0);

        // then
        assertThat(ownerRepository.count()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("green");
        assertThat(owner.getName()).isEqualTo("green");
    }

    @Test
    @DisplayName("이름 중복으로 인한 회원가입 실패")
    void registerFail() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        OwnerRegisterRequestDto request = OwnerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        // expect
        assertThatThrownBy(() -> ownerRegisterService.register(request))
                .isInstanceOf(OwnerNameDuplicateException.class);
    }
}
