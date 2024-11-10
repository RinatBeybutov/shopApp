package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.ProductEntity;
import com.petProject.OrderService.entity.ProductToOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductViewDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductCreateDto productViewDto);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "uuid", source = "product.uuid")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "categoryId", source = "product.categoryId")
    ProductWithCountViewDto toCountDto(ProductToOrderEntity entity);
}
