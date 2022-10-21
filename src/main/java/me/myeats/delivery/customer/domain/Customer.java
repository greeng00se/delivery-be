package me.myeats.delivery.customer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.jwt.AuthRole;
import me.myeats.delivery.common.time.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AUTHORITIES")
    @Enumerated(EnumType.STRING)
    private AuthRole authority;

    @Builder
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = AuthRole.ROLE_CUSTOMER;
    }
}
