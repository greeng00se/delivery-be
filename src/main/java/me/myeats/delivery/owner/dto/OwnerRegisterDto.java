package me.myeats.delivery.owner.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OwnerRegisterDto {

    private OwnerRegisterDto() {
    }

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

        @Builder
        public Request(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }

    @Data
    public static class Response {

        private final String name;
    }

}
