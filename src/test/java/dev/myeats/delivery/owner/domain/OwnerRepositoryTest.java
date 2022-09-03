package dev.myeats.delivery.owner.domain;

import dev.myeats.delivery.common.jwt.AuthRole;
import dev.myeats.delivery.common.jwt.Authority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("Authorities 포함 이름을 통한 단일 조회")
    void findOneWithAuthoritiesByName() {
        // given
        Authority authority = Authority.builder()
                .authorityName(AuthRole.ROLE_OWNER)
                .build();

        Owner owner = Owner.builder()
                .name("green")
                .email("hello@naver.com")
                .password("goose")
                .authorities(Collections.singleton(authority))
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        // when
        Owner findOwner = ownerRepository.findOneWithAuthoritiesByName("green").get();

        // then
        assertThat(findOwner).isEqualTo(owner);
        assertThat(findOwner.getAuthorities()).containsExactly(authority);
    }
}
