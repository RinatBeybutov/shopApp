package com.petProject.OrderService.mapper;

import com.petProject.OrderService.dto.OrderProductCreateDto;
import com.petProject.OrderService.entity.ProductToOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    ProductToOrderEntity toEntity(OrderProductCreateDto dto, Integer orderId);
}
