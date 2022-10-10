package me.myeats.delivery.customer.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.customer.dto.request.CustomerLoginRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerLoginResponseDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerLoginService {

    private final TokenProvider tokenProvider;

    public CustomerLoginResponseDto login(CustomerLoginRequestDto loginDto) {
        return new CustomerLoginResponseDto("Hello");
    }
}
