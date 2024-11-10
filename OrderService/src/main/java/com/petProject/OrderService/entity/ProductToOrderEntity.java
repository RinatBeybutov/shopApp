package com.petProject.OrderService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_to_order", schema = "order_service")
public class ProductToOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "product_count")
    private Integer count;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
