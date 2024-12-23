package com.petProject.UserService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "user_service")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "registered_at")
    private LocalDate registeredAt;

    @Column(name = "email")
    private String email;

    @PrePersist
    private void generateUuid() {
        setUuid(UUID.randomUUID());
    }

}
