package me.myeats.delivery.customer.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerLoginResponseDto {
    private String token;

    @Builder
    public CustomerLoginResponseDto(String token) {
        this.token = token;
    }
}
