package me.myeats.delivery.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.common.time.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Shop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OPEN")
    private boolean open;

    @Column(name = "MIN_ORDER_AMOUNT")
    private Money minOrderAmount;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "OWNER_ID")
    private Long ownerId;

    @Builder
    public Shop(String name, boolean open, Money minOrderAmount, String address, String phoneNumber, Long ownerId) {
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ownerId = ownerId;
    }
}
