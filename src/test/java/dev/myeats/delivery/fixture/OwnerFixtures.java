package dev.myeats.delivery.fixture;

import dev.myeats.delivery.common.jwt.AuthRole;
import dev.myeats.delivery.common.jwt.Authority;
import dev.myeats.delivery.owner.domain.Owner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class OwnerFixtures {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Owner.OwnerBuilder owner() {
        Authority authority = authority().build();
        
        return Owner.builder()
                .name("green")
                .email("green@naver.com")
                .password(passwordEncoder.encode("goose"))
                .authorities(Collections.singleton(authority));
    }

    public static Authority.AuthorityBuilder authority() {
        return Authority.builder()
                .authorityName(AuthRole.ROLE_OWNER);
    }
}
