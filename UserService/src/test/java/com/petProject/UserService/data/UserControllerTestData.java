package com.petProject.UserService.data;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.UserEntity.RangEnum;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Тестовые данные для контроллера пользователей
 */
public class UserControllerTestData {

  /**
   * Получение тестового объекта UserDto
   */
  public static UserViewDto getViewUserDto() {
    UserViewDto userViewDto = new UserViewDto();
    userViewDto.setEmail("abc@mail.ru");
    userViewDto.setName("Gaben");
    userViewDto.setRegisteredAt(LocalDate.parse("2024-09-19"));
    userViewDto.setUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"));
    userViewDto.setRang(RangEnum.NO_RANG);
    userViewDto.setOrderCount(0);
    return userViewDto;
  }

  /**
   * Получение тестового объекта для создания пользователя
   */
  public static UserCreateDto getCreateUserDto() {
    UserCreateDto dto = new UserCreateDto();
    dto.setEmail("abc@mail.ru");
    dto.setName("Gaben");
    return dto;
  }

  /**
   * Получение тестового объекта после создания пользователя
   */
  public static UserViewDto createdViewDto() {
    UserViewDto userViewDto = new UserViewDto();
    userViewDto.setEmail("abc@mail.ru");
    userViewDto.setName("Gaben");
    userViewDto.setRang(RangEnum.NO_RANG);
    userViewDto.setOrderCount(0);
    return userViewDto;
  }
}
