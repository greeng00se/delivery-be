package me.myeats.delivery.shop.domain;

import lombok.NoArgsConstructor;
import me.myeats.delivery.common.money.Money;

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

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    private String name;
    private String description;
    private Money price;
    private Long priority;
    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<OptionGroupSpec> optionGroupSpecs = new ArrayList<>();

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
