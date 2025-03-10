package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserDto;
import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService {

  UserDto save(UserDto userDto);

  List<UserDto> getUsers();

  UserDto getUserById(Integer id);
}
