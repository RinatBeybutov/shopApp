package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;

import java.util.List;

public interface OrderService {
    List<OrderViewDto> getOrders();

    OrderViewDto create(OrderCreateDto dto);

    void delete(Integer orderId);
}
