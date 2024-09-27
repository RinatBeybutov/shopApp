package com.petProject.UserService.mapper;

import com.petProject.UserService.dto.UserDto;
import com.petProject.UserService.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto dto);

    //@Mapping(target = "name", source = "name")
    //@Mapping(target = "email", source = "email")
    //@Mapping(target = "registeredAt", source = "registeredAt")
    UserDto toDto(UserEntity entity);

    //UserEntity toEntity(UserDto userDto);

    //UserDto toDto(UserEntity entity);

}
