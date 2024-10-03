package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderViewDto toDto(OrderEntity orderEntity);

    OrderEntity toEntity(OrderCreateDto dto);
}
