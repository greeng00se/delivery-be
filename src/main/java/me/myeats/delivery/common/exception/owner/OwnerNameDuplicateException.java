package me.myeats.delivery.common.exception.owner;

import org.springframework.http.HttpStatus;

public class OwnerNameDuplicateException extends OwnerException {
    private static final String CODE = "O001";
    private static final String MESSAGE = "이름 충돌";

    public OwnerNameDuplicateException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
