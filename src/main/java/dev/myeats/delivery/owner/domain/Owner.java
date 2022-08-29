package dev.myeats.delivery.owner.domain;

import dev.myeats.delivery.common.time.BaseTimeEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Owner extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;

    @Builder
    public Owner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
