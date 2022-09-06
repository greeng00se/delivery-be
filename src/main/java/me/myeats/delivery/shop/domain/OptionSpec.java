package me.myeats.delivery.shop.domain;

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
public class OptionSpec extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "OPTION_SPEC_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Money price;

    public OptionSpec(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
