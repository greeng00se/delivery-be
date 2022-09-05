package me.myeats.delivery.owner.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OwnerLoginDto {

    @Data
    public static class Request {

        @NotNull
        @Size(min = 3, max = 100)
        private final String name;

        @NotNull
        @Size(min = 3, max = 100)
        private final String password;

        @Builder
        public Request(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    @Data
    public static class Response {
        private final String token;
    }

}
