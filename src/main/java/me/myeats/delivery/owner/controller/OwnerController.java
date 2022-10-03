package me.myeats.delivery.owner.controller;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.JwtFilter;
import me.myeats.delivery.owner.dto.request.OwnerLoginRequestDto;
import me.myeats.delivery.owner.dto.request.OwnerRegisterRequestDto;
import me.myeats.delivery.owner.dto.response.OwnerLoginResponseDto;
import me.myeats.delivery.owner.dto.response.OwnerRegisterResponseDto;
import me.myeats.delivery.owner.service.OwnerLoginService;
import me.myeats.delivery.owner.service.OwnerRegisterService;
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
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerRegisterService ownerRegisterService;
    private final OwnerLoginService ownerLoginService;

    @PostMapping("/register")
    public OwnerRegisterResponseDto register(@Valid @RequestBody OwnerRegisterRequestDto registerDto) {
        return ownerRegisterService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<OwnerLoginResponseDto> login(@Valid @RequestBody OwnerLoginRequestDto loginDto) {
        OwnerLoginResponseDto ownerLoginResponseDto = ownerLoginService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + ownerLoginResponseDto.getToken());

        return new ResponseEntity<>(ownerLoginResponseDto, httpHeaders, HttpStatus.OK);
    }
}
