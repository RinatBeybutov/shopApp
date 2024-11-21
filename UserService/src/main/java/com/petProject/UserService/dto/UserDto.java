package com.petProject.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Сущность пользователя")
public class UserDto {

    @Schema(description = "Имя пользователя")
    private String name;

    @Schema(description = "Дата регистрации пользователя")
    private LocalDate registeredAt;

    @Schema(description = "Email пользователя")
    private String email;

    private UUID uuid;
}
