package me.myeats.delivery.common.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum AuthRole {
    ROLE_OWNER(new SimpleGrantedAuthority("ROLE_OWNER")),
    ROLE_CUSTOMER(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

    private final GrantedAuthority authority;
}
