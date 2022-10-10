package me.myeats.delivery.customer.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerRegisterRequestDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Email
    @Size(min = 8, max = 100)
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public CustomerRegisterRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
