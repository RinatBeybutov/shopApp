package com.petProject.UserService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность пользователя
 */
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
