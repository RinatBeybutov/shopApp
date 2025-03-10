package com.petProject.UserService.IntegreationTests;

import com.petProject.UserService.dto.UserDto;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Тестовые данные для контроллера пользователей
 */
public class UserControllerTestData {

  /**
   * Получение тестового объекта UserDto
   */
  public static UserDto getViewUserDto() {
    UserDto userDto = new UserDto();
    userDto.setEmail("abc@mail.ru");
    userDto.setName("Gaben");
    userDto.setRegisteredAt(LocalDate.parse("2024-09-19"));
    userDto.setUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"));
    return userDto;
  }
}
