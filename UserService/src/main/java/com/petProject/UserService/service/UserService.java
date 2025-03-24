package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService {

  UserViewDto save(UserCreateDto userViewDto);

  List<UserViewDto> getUsers();

  UserViewDto getUserById(UUID id);

  void increaseOrderCount(UUID userId);

  void delete(UUID uuid);
}
