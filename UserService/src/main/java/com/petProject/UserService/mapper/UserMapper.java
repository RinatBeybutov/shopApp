package com.petProject.UserService.mapper;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Mapper для преобразования пользователей
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toEntity(UserCreateDto dto);

  UserViewDto toDto(UserEntity entity);
}
