package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.OrderEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderViewDto toDto(OrderEntity orderEntity, List<ProductWithCountViewDto> products);

    OrderEntity toEntity(OrderCreateDto dto);
}
