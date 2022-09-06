package me.myeats.delivery.common.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class TokenProviderTest {

    private static TokenProvider tokenProvider;
    private static JwtParser jwtParser;

    @BeforeAll
    static void beforeAll() throws Exception {
        String secret = RandomString.make(96);
        tokenProvider = new TokenProvider(secret, 3600L);
        tokenProvider.afterPropertiesSet();

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    @Test
    @DisplayName("토큰을 생성한다.")
    void createToken() {
        // given
        Authentication authentication = createAuthentication();

        // when
        String token = tokenProvider.createToken(authentication);

        // then
        assertThat(jwtParser.isSigned(token)).isTrue();
    }

    @Test
    @DisplayName("생성된 토큰을 검증한다.")
    void validateToken() {
        // given
        Authentication authentication = createAuthentication();
        String token = tokenProvider.createToken(authentication);

        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isTrue();
    }

    private Authentication createAuthentication() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("OWNER"));
        return new UsernamePasswordAuthenticationToken("user", "password", authorities);
    }

}
