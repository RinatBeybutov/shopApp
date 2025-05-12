package com.petProject.UserService.mapper;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper для преобразования пользователей
 *
 * @author Rinat B
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserEntity toEntity(UserCreateDto dto);

  UserViewDto toDto(UserEntity entity);

  void update(@MappingTarget UserEntity entity, UserCreateDto updateDto);
}
