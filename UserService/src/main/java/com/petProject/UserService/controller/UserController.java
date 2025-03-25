package com.petProject.UserService.controller;

import static com.petProject.UserService.config.API.USER_API;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями. {@link UserEntity}
 *
 * @author Rinat B
 */
@RestController
@RequestMapping(USER_API)
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для работы с пользователями")
public class UserController {

  private final UserService userService;

  @PostMapping
  @Operation(summary = "Создание пользователя")
  public ResponseEntity<UserViewDto> createUser(@RequestBody UserCreateDto userViewDto) {
    return ResponseEntity.ok(userService.create(userViewDto));
  }

  @GetMapping
  @Operation(summary = "Получение списка всех пользователей")
  public ResponseEntity<List<UserViewDto>> getUsers() {
    return ResponseEntity.ok(userService.getList());
  }

  @GetMapping("/{uuid}")
  @Operation(summary = "Получение пользователя по его идентификатору")
  public ResponseEntity<UserViewDto> getUserById(@PathVariable
                                             @Parameter(description = "Идентификатор пользователя")
                                             UUID uuid) {
    return ResponseEntity.ok(userService.getOne(uuid));
  }

  @DeleteMapping("/{uuid}")
  @Operation(summary = "Удаление пользователя по его идентификатору")
  public ResponseEntity<Void> deleteUserById(@PathVariable
                                             @Parameter(description = "Идентификатор пользователя")
                                             UUID uuid) {
    userService.delete(uuid);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{uuid}")
  @Operation(summary = "Обновление пользователя по его идентификатору")
  public ResponseEntity<UserViewDto> update(@PathVariable
                                                    @Parameter(description = "Идентификатор пользователя")
                                                    UUID uuid,
                                                    @RequestBody UserCreateDto userUpdateDto) {
    return ResponseEntity.ok(userService.update(uuid, userUpdateDto));
  }
}
