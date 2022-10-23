package me.myeats.delivery.common.exception.shop;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ShopNotFoundException extends ApplicationException {

    private static final String CODE = "S001";
    private static final String MESSAGE = "상점을 찾을 수 없음";

    public ShopNotFoundException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
