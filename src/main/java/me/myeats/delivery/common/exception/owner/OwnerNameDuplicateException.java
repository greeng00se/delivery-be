package me.myeats.delivery.common.exception.owner;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class OwnerNameDuplicateException extends ApplicationException {

    private static final String CODE = "O001";
    private static final String MESSAGE = "이름 충돌";

    public OwnerNameDuplicateException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
