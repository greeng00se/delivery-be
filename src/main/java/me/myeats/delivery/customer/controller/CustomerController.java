package me.myeats.delivery.customer.controller;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.JwtFilter;
import me.myeats.delivery.customer.dto.request.CustomerLoginRequestDto;
import me.myeats.delivery.customer.dto.request.CustomerRegisterRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerLoginResponseDto;
import me.myeats.delivery.customer.dto.response.CustomerRegisterResponseDto;
import me.myeats.delivery.customer.service.CustomerLoginService;
import me.myeats.delivery.customer.service.CustomerRegisterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRegisterService customerRegisterService;
    private final CustomerLoginService customerLoginService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponseDto> register(
            @Valid @RequestBody CustomerRegisterRequestDto registerDto
    ) {
        CustomerRegisterResponseDto response = customerRegisterService.register(registerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponseDto> login(
            @Valid @RequestBody CustomerLoginRequestDto loginDto
    ) {
        CustomerLoginResponseDto response = customerLoginService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + response.getToken());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(response);
    }
}
