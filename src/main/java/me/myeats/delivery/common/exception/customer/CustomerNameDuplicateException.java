package me.myeats.delivery.common.exception.customer;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CustomerNameDuplicateException extends ApplicationException {

    private static final String CODE = "C001";
    private static final String MESSAGE = "이름 충돌";

    public CustomerNameDuplicateException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
