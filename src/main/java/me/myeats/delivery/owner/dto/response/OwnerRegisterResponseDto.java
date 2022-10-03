package me.myeats.delivery.owner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerRegisterResponseDto {

    private String name;

    @Builder
    public OwnerRegisterResponseDto(String name) {
        this.name = name;
    }
}
