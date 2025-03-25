package com.petProject.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для создания пользователя
 *
 * @author Rinat B
 */
@Getter
@Setter
public class UserCreateDto {

  @Schema(description = "Имя пользователя")
  private String name;

  @Schema(description = "Email пользователя")
  private String email;
}
