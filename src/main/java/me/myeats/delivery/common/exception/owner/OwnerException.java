package me.myeats.delivery.common.exception.owner;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class OwnerException extends ApplicationException {
    protected OwnerException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
