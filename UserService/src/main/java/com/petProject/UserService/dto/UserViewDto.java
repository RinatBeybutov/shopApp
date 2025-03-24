package com.petProject.UserService.dto;

import com.petProject.UserService.entity.UserEntity.RangEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Дто пользователя
 */
@Getter
@Setter
@Schema(description = "Сущность пользователя")
public class UserViewDto {

  @Schema(description = "Имя пользователя")
  private String name;

  @Schema(description = "Дата регистрации пользователя")
  private LocalDate registeredAt;

  @Schema(description = "Email пользователя")
  private String email;

  @Schema(description = "Ранг пользователя")
  private RangEnum rang;

  private UUID uuid;

  private Integer orderCount;
}
