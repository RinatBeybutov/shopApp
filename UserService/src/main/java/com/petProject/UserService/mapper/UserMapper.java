package com.petProject.UserService.mapper;

import com.petProject.UserService.dto.UserDto;
import com.petProject.UserService.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto dto);

    UserDto toDto(UserEntity entity);
}
