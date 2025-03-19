package com.petProject.MetricsService.service;

import com.petProject.MetricsService.dto.OrderKafkaDto;
import com.petProject.MetricsService.entity.OrderEntity;
import com.petProject.MetricsService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public void saveOrder(OrderKafkaDto order) {
    OrderEntity entity = toEntity(order);
    orderRepository.save(entity);
  }

  private OrderEntity toEntity(OrderKafkaDto order) {
    return new OrderEntity(order.getOrderUuid(), order.getTime());
  }
}
