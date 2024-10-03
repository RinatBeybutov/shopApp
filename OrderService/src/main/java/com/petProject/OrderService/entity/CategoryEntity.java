package com.petProject.OrderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @PrePersist
    private void generateUuid() {
        setUuid(UUID.randomUUID());
    }
}
