package me.myeats.delivery.common.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.EnumMap;

public class UserDetailsServiceProvider {

    private EnumMap<AuthRole, UserDetailsService> userDetailsServiceEnumMap;

    public UserDetailsServiceProvider() {
        this.userDetailsServiceEnumMap = new EnumMap<>(AuthRole.class);
    }

    public void put(AuthRole role, UserDetailsService userDetailsService) {
        this.userDetailsServiceEnumMap.put(role, userDetailsService);
    }

    public UserDetailsService get(AuthRole authRole) {
        return this.userDetailsServiceEnumMap.get(authRole);
    }
}
