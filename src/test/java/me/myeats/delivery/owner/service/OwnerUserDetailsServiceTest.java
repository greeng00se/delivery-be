package me.myeats.delivery.owner.service;

import me.myeats.delivery.common.jwt.owner.OwnerUserDetailsService;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.test.fixture.OwnerFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OwnerUserDetailsServiceTest {

    @Autowired
    private OwnerUserDetailsService ownerUserDetailsService;

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("loadUserByUsername - 정상")
    void loadUserByUsername() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        // when
        UserDetails userDetails = ownerUserDetailsService.loadUserByUsername("green");

        // then
        assertThat(userDetails.getUsername()).isEqualTo("green");
    }

    @Test
    @DisplayName("loadUserByUsername - 사용자 없음")
    void loadUserByUsernameException() {
        // expect
        assertThatThrownBy(() -> ownerUserDetailsService.loadUserByUsername("green"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
