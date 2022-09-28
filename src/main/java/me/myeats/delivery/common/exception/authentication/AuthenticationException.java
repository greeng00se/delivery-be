package me.myeats.delivery.common.exception.authentication;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class AuthenticationException extends ApplicationException {

    protected AuthenticationException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
