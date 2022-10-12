package me.myeats.delivery.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

/**
 * Jwt Custom Filter + Token Provider를 Security 로직에 등록하는 설정 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final EnumMap<AuthRole, UserDetailsService> userDetailsServiceEnumMap;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter filter = new JwtFilter(tokenProvider, userDetailsServiceEnumMap);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
