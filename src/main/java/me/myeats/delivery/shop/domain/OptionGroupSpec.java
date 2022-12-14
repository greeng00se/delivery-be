package me.myeats.delivery.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class OptionGroupSpec extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "OPTION_GROUP_SPEC_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXCLUSIVE")
    private boolean exclusive;

    @Column(name = "BASIC")
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPTION_GROUP_SPEC_ID")
    private List<OptionSpec> optionSpecs = new ArrayList<>();

    @Builder
    public OptionGroupSpec(String name, boolean exclusive, boolean basic,
                           List<OptionSpec> optionSpecs) {
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs = optionSpecs;
    }
}
