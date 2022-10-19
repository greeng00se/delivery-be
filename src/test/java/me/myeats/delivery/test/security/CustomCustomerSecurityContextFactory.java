package me.myeats.delivery.test.security;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.customer.CustomerUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

import static me.myeats.delivery.common.jwt.AuthRole.ROLE_CUSTOMER;

@RequiredArgsConstructor
public class CustomCustomerSecurityContextFactory implements WithSecurityContextFactory<WithCustomCustomer> {

    private final CustomerUserDetailsService customerUserDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithCustomCustomer customer) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = customerUserDetailsService.loadUserByUsername(customer.username());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, "", List.of(ROLE_CUSTOMER.getAuthority()));
        context.setAuthentication(auth);
        return context;
    }
}
