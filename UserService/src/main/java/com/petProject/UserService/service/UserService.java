package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUserById(Integer id);
}
