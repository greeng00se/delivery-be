package me.myeats.delivery.test.fixture;

import me.myeats.delivery.customer.domain.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomerFixtures {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Customer.CustomerBuilder customer() {
        return Customer.builder()
                .name("green")
                .email("green@naver.com")
                .password(passwordEncoder.encode("goose"));
    }
}
