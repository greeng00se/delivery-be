package me.myeats.delivery.customer.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerLoginRequestDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public CustomerLoginRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
