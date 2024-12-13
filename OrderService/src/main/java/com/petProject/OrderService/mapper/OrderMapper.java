package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    //@Mapping(target = "products", source = "products")
    OrderViewDto toDto(OrderEntity orderEntity, List<ProductWithCountViewDto> products);

    OrderEntity toEntity(OrderCreateDto dto);
}
