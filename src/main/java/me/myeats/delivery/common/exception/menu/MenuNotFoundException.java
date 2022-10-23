package me.myeats.delivery.common.exception.menu;

import me.myeats.delivery.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class MenuNotFoundException extends ApplicationException {

    private static final String CODE = "M001";
    private static final String MESSAGE = "메뉴를 찾을 수 없음";

    public MenuNotFoundException() {
        super(MESSAGE, CODE, HttpStatus.BAD_REQUEST);
    }
}
