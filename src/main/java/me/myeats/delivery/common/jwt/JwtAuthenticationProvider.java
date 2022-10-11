package me.myeats.delivery.common.jwt;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final EnumMap<AuthRole, UserDetailsService> userDetailsServiceEnumMap;

    @Override
    public Authentication authenticate(Authentication authentication) {
        UserDetails userDetails = getUserDetails(authentication).orElseThrow(UnauthorizedException::new);
        if (!isAuthorized(authentication, userDetails)) {
            throw new UnauthorizedException();
        }
        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                authentication.getCredentials().toString(),
                userDetails.getAuthorities()
        );
    }

    private Optional<UserDetails> getUserDetails(Authentication authentication) {
        String username = authentication.getName();
        return authentication.getAuthorities()
                .stream()
                // Service에서 넘긴 Authority로 Customer, Owner 구분 findFirst로 Authority을 가져온다.
                .findFirst()
                // Authority -> AuthRole
                .map(authority -> AuthRole.valueOf(authority.getAuthority()))
                // AuthRole에 해당하는 UserDetailsService를 이용하여 사용자의 정보를 가져온다.
                .map(authRole -> userDetailsServiceEnumMap.get(authRole).loadUserByUsername(username));
    }

    private boolean isAuthorized(Authentication authentication, UserDetails userDetails) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        return userDetails.getUsername().equals(username) &&
                passwordEncoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
