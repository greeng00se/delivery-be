package me.myeats.delivery.common.jwt;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.customer.CustomerUserDetailsService;
import me.myeats.delivery.common.jwt.owner.OwnerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceProviderConfig {

    private final OwnerUserDetailsService ownerUserDetailsService;
    private final CustomerUserDetailsService customerUserDetailsService;

    @Bean
    public UserDetailsServiceProvider userDetailsServiceProvider() {
        UserDetailsServiceProvider userDetailsServiceProvider = new UserDetailsServiceProvider();
        userDetailsServiceProvider.put(AuthRole.ROLE_OWNER, ownerUserDetailsService);
        userDetailsServiceProvider.put(AuthRole.ROLE_CUSTOMER, customerUserDetailsService);
        return userDetailsServiceProvider;
    }
}
