package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;
import com.petProject.OrderService.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryViewDto toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryCreateDto dto);
}
