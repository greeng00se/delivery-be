package me.myeats.delivery.common.exception.authentication;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AuthenticationException {

    private static final String CODE = "A001";
    private static final String MESSAGE = "권한 없음";

    public UnauthorizedException() {
        super(MESSAGE, CODE, HttpStatus.UNAUTHORIZED);
    }
}
