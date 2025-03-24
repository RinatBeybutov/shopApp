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
import lombok.AccessLevel;
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

  /**
   * Имя пользователя
   */
  @Column(name = "name")
  private String name;

  /**
   * Дата регистрации пользователя
   */
  @Column(name = "registered_at")
  private LocalDate registeredAt;

  /**
   * Email пользователя
   */
  @Column(name = "email")
  private String email;

  /**
   * Количество заказов пользователя
   */
  @Column(name = "order_count", nullable = false)
  private Integer orderCount;

  @Column(name = "rang", nullable = false)
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private Integer rang;

  public RangEnum getRang() {
    return RangEnum.valueOf(rang);
  }

  public void setRang(RangEnum rang) {
    this.rang = rang.getValue();
  }

  @PrePersist
  private void generateUuid() {
    setUuid(UUID.randomUUID());
  }

  /**
   * Ранг пользователя
   */
  public static enum RangEnum {

    NO_RANG(0, 0),
    BEGINNER(1, 5),
    HERO(2, 10),
    SENSEI(3, 20),
    MASTER(4, 40);

    @Getter
    private final int value;

    @Getter
    private final int orderLimit;

    RangEnum(int value, int orderLimit) {
      this.value = value;
      this.orderLimit = orderLimit;
    }

    /**
     * Возвращает ранг пользователя по значению
     */
    public static RangEnum valueOf(Integer rang) {
      for (RangEnum rangEnum : RangEnum.values()) {
        if (rang == rangEnum.getValue()) {
          return rangEnum;
        }
      }
      throw new IllegalArgumentException("Invalid rang value");
    }

    /**
     * Возвращает следующий ранг пользователя
     */
    public static RangEnum getNextRang(RangEnum currentRang) {
      for (RangEnum rangEnum : RangEnum.values()) {
        if (rangEnum.getValue() > currentRang.getValue()) {
          return rangEnum;
        }
      }
      throw new IllegalArgumentException("Invalid rang value");
    }
  }

}
