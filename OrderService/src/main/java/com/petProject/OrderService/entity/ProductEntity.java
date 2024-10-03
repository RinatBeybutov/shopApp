package com.petProject.OrderService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "order_service")
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @Column(name = "category_id", updatable = false, insertable = false)
    private Integer categoryId;

    @PrePersist
    private void generateUuid() {
        setUuid(UUID.randomUUID());
    }
}
