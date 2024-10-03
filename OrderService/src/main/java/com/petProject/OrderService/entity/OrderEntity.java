package com.petProject.OrderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "order_service")
public class OrderEntity extends BaseEntity {

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @PrePersist
    private void generateUuid() {
        setUuid(UUID.randomUUID());
    }
}
