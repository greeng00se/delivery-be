package me.myeats.delivery.owner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerLoginResponseDto {
    private String token;

    @Builder
    public OwnerLoginResponseDto(String token) {
        this.token = token;
    }
}
