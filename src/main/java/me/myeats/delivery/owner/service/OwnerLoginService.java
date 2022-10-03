package me.myeats.delivery.owner.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.owner.dto.request.OwnerLoginRequestDto;
import me.myeats.delivery.owner.dto.response.OwnerLoginResponseDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerLoginService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public OwnerLoginResponseDto login(OwnerLoginRequestDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new OwnerLoginResponseDto(tokenProvider.createToken(authentication));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException();
        }
    }
}
