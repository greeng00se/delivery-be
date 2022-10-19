package me.myeats.delivery.common.exception.order;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidOrderException extends ApplicationException {

    private static final String CODE = "OD001";
    private static final String MESSAGE = "올바르지 않은 주문";

    public InvalidOrderException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
