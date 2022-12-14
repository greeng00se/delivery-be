package me.myeats.delivery.common.jwt.customer;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.customer.domain.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String name) {
        return customerRepository.findOneByName(name)
                .map(CustomerUserDetails::new)
                .orElseThrow(UnauthorizedException::new);
    }
}
