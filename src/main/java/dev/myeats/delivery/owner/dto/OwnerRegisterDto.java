package dev.myeats.delivery.owner.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OwnerRegisterDto {

    @Data
    public static class Request {

        @NotNull
        @Size(min = 3, max = 100)
        private final String name;

        @NotNull
        @Email
        @Size(min = 8, max = 100)
        private final String email;

        @NotNull
        @Size(min = 3, max = 100)
        private final String password;
    }

    @Data
    public static class Response {

        private final String name;
    }

}
