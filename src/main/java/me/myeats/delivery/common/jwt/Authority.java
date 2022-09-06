package me.myeats.delivery.common.jwt;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Authority {

    @Id
    @Column(name = "AUTHORITY_NAME", length = 50)
    @Enumerated(EnumType.STRING)
    private AuthRole authorityName;

    @Builder
    public Authority(AuthRole authorityName) {
        this.authorityName = authorityName;
    }
}
