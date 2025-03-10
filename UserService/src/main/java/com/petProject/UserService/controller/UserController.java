package com.petProject.UserService.controller;

import static com.petProject.UserService.config.API.USER_API;

import com.petProject.UserService.dto.UserDto;
import com.petProject.UserService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(USER_API)
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для работы с пользователями")
public class UserController {

  private final UserService userService;

  @PostMapping
  @Operation(summary = "Создание пользователя")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.save(userDto));
  }

  @GetMapping
  @Operation(summary = "Получение списка всех пользователей")
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получение пользователя по его идентификатору")
  public ResponseEntity<UserDto> getUserById(@PathVariable
                                             @Parameter(description = "Идентификатор пользователя")
                                             Integer id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }
}
