package me.myeats.delivery.common.exception.authentication;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationException {

    private static final String CODE = "A001";
    private static final String MESSAGE = "권한 없음";

    public UnauthorizedException() {
        super(MESSAGE, CODE, HttpStatus.UNAUTHORIZED);
    }
}
