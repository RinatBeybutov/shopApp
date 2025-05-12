package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с пользователями.
 *
 * @author Rinat B
 */
public interface UserService {

  UserViewDto create(UserCreateDto userViewDto);

  UserViewDto update(UUID userUuid, UserCreateDto updateDto);

  List<UserViewDto> getList();

  UserViewDto getOne(UUID uuid);

  void increaseOrderCount(UUID userUuid);

  void delete(UUID uuid);
}
