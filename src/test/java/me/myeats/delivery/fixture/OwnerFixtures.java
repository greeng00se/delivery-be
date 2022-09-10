package me.myeats.delivery.fixture;

import me.myeats.delivery.owner.domain.Owner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class OwnerFixtures {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Owner.OwnerBuilder owner() {
        return Owner.builder()
                .name("green")
                .email("green@naver.com")
                .password(passwordEncoder.encode("goose"));
    }
}
