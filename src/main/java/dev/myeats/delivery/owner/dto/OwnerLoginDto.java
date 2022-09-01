package dev.myeats.delivery.owner.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OwnerLoginDto {

    @Data
    public static class Request {

        @NotNull
        @Email
        @Size(min = 3, max = 100)
        private final String username;

        @NotNull
        @Size(min = 3, max = 100)
        private final String password;
    }

    @Data
    public static class Response {
        private final String token;
    }

}
