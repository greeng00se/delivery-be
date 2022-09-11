package me.myeats.delivery.common.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("토큰을 생성하고 검증한다.")
    void validateToken() {
        // given
        Authentication authentication = createAuthentication();
        String token = tokenProvider.createToken(authentication);

        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("토큰에서 Username을 가져온다.")
    void getUsernameFromToken() {
        // given
        Authentication authentication = createAuthentication();
        String token = tokenProvider.createToken(authentication);

        // when
        String username = tokenProvider.getUsernameFromToken(token);

        // then
        assertThat(username).isEqualTo("user");
    }

    private Authentication createAuthentication() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("OWNER"));
        return new UsernamePasswordAuthenticationToken("user", "password", authorities);
    }

}
