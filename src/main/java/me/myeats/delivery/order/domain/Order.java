package me.myeats.delivery.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.myeats.delivery.common.time.BaseTimeEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "ORDERS")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @Column(name = "ORDERED_TIME")
    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Builder
    public Order(Long userId, Long shopId, LocalDateTime orderedTime, OrderStatus orderStatus, List<OrderLineItem> orderLineItems) {
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.orderLineItems = orderLineItems;
    }

    public void paid() {
        this.orderStatus = OrderStatus.PAYED;
    }

    public void delivered() {
        this.orderStatus = OrderStatus.DELIVERED;
    }
}
