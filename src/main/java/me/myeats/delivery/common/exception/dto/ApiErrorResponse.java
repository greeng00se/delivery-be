package me.myeats.delivery.common.exception.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiErrorResponse {

    private String errorCode;

    public ApiErrorResponse(String errorCode) {
        this.errorCode = errorCode;
    }
}
