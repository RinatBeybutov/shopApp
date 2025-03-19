package com.petProject.MetricsService.repository;

import com.petProject.MetricsService.entity.OrderEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private static final String KEY = "Order";

  private final RedissonClient redissonClient;

  public void save(OrderEntity orderEntity) {
    redissonClient.getMap(KEY).put(orderEntity.getOrderUuid(), orderEntity);
  }

  public List<OrderEntity> getList() {
    return redissonClient.getMap(KEY).values()
        .stream()
        .filter(OrderEntity.class::isInstance)
        .map(OrderEntity.class::cast)
        .toList();
  }

  public void clear() {
    redissonClient.getMap(KEY).clear();
  }

}
