package me.myeats.delivery.customer.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.customer.dto.request.CustomerLoginRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerLoginResponseDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.myeats.delivery.common.jwt.AuthRole.ROLE_CUSTOMER;

@RequiredArgsConstructor
@Service
public class CustomerLoginService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public CustomerLoginResponseDto login(CustomerLoginRequestDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getName(),
                loginDto.getPassword(),
                List.of(ROLE_CUSTOMER.getAuthority())
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new CustomerLoginResponseDto(tokenProvider.createToken(authentication));
    }
}
