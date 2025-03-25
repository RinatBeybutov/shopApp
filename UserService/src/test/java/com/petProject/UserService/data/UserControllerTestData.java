package com.petProject.UserService.data;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.RangEnum;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Тестовые данные для контроллера пользователей
 *
 * @author Rinat B
 */
public class UserControllerTestData {

  public static final UUID WRONG_UUID = UUID.fromString("974df0be-8fe6-4cb8-8e71-b307567c3e60");

  public static final String USER_NOT_FOUND_MESSAGE = "Пользователь с таким uuid %s не найден";

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

  public static UserCreateDto userUpdateDto() {
    UserCreateDto userCreateDto = new UserCreateDto();
    userCreateDto.setEmail("new@mail.ru");
    userCreateDto.setName("New Gaben");
    return userCreateDto;
  }

  public static UserViewDto updatedUserDto() {
    UserViewDto userViewDto = new UserViewDto();
    userViewDto.setEmail("new@mail.ru");
    userViewDto.setName("New Gaben");
    userViewDto.setOrderCount(0);
    userViewDto.setRang(RangEnum.NO_RANG);
    return userViewDto;
  }
}
