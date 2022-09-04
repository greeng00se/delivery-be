package dev.myeats.delivery.owner.service;

import dev.myeats.delivery.fixture.OwnerFixtures;
import dev.myeats.delivery.owner.domain.Owner;
import dev.myeats.delivery.owner.domain.OwnerRepository;
import dev.myeats.delivery.owner.dto.OwnerRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OwnerRegisterServiceTest {

    @Autowired
    private OwnerRegisterService ownerRegisterService;

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        // given
        OwnerRegisterDto.Request request = OwnerFixtures.registerRequest().build();

        // when
        OwnerRegisterDto.Response response = ownerRegisterService.register(request);
        Owner owner = ownerRepository.findAll().get(0);

        // then
        assertThat(ownerRepository.count()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("green");
        assertThat(owner.getName()).isEqualTo("green");
    }

    @Test
    @DisplayName("회원가입 실패")
    void registerFail() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);
        OwnerRegisterDto.Request request = OwnerFixtures.registerRequest().build();

        // expect
        assertThatThrownBy(() -> ownerRegisterService.register(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
