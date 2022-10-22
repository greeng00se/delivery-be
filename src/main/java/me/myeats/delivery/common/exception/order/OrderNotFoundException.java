package me.myeats.delivery.common.exception.order;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ApplicationException {

    private static final String CODE = "OD002";
    private static final String MESSAGE = "주문을 찾을 수 없음";

    public OrderNotFoundException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
