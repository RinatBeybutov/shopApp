package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductViewDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductCreateDto productViewDto);
}
