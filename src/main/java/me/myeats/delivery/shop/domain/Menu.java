package me.myeats.delivery.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.common.time.BaseTimeEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Money price;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<OptionGroupSpec> optionGroupSpecs = new ArrayList<>();

    @Builder
    public Menu(String name, String description, Money price, Long priority, Long shopId,
                List<OptionGroupSpec> optionGroupSpecs) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.shopId = shopId;
        this.optionGroupSpecs = optionGroupSpecs;
    }
}
