package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderViewDto> getOrders(UUID userId);

    OrderViewDto create(OrderCreateDto dto);

    void delete(Integer orderId);
}
