package me.myeats.delivery.common.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiErrorResponse {

    private final String errorCode;
}
