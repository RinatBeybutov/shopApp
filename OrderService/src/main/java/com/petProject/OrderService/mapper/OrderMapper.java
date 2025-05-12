package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.OrderEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderViewDto toDto(OrderEntity orderEntity, List<ProductWithCountViewDto> products);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    OrderEntity toEntity(OrderCreateDto dto);
}
