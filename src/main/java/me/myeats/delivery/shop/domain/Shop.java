package me.myeats.delivery.shop.domain;

import lombok.Builder;
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
@NoArgsConstructor(access = PROTECTED)
public class Shop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;

    private boolean open;
    private Money minOrderAmount;
    private Long ownerId;

    @Builder
    public Shop(boolean open, Money minOrderAmount, Long ownerId) {
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.ownerId = ownerId;
    }
}
