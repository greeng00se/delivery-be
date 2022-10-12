package me.myeats.delivery.customer.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerRegisterResponseDto {

    private String name;

    @Builder
    public CustomerRegisterResponseDto(String name) {
        this.name = name;
    }
}
