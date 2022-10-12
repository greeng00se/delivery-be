package me.myeats.delivery.common.jwt;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.customer.CustomerUserDetailsService;
import me.myeats.delivery.common.jwt.owner.OwnerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.EnumMap;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceProviderConfig {

    private final OwnerUserDetailsService ownerUserDetailsService;
    private final CustomerUserDetailsService customerUserDetailsService;

    @Bean
    public EnumMap<AuthRole, UserDetailsService> userDetailsServiceEnumMap() {
        EnumMap<AuthRole, UserDetailsService> userDetailsServiceEnumMap = new EnumMap<>(AuthRole.class);
        userDetailsServiceEnumMap.put(AuthRole.ROLE_OWNER, ownerUserDetailsService);
        userDetailsServiceEnumMap.put(AuthRole.ROLE_CUSTOMER, customerUserDetailsService);
        return userDetailsServiceEnumMap;
    }
}
