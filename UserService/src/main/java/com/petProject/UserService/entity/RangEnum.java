package com.petProject.UserService.entity;

import lombok.Getter;

/**
 * Ранг пользователя
 *
 * @author Rinat B
 */
public enum RangEnum {
  /**
   * Новый пользователь
   */
  NO_RANG(0, 0),
  /**
   * Новичок с 5 заказами
   */
  BEGINNER(1, 5),
  /**
   * Герой с 10 заказами
   */
  HERO(2, 10),
  /**
   * Сенсей с 20 заказами
   */
  SENSEI(3, 20),
  /**
   * Мастер с 40 заказами
   */
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
