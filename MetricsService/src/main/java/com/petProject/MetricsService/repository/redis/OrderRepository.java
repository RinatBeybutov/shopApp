package com.petProject.MetricsService.repository.redis;

import com.petProject.MetricsService.entity.redis.OrderEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private static final String KEY = "Order";

  private final RedissonClient redissonClient;

  public void save(OrderEntity orderEntity) {
    log.debug("Сохранение заказа {}", orderEntity);
    redissonClient.getMap(KEY).put(orderEntity.getOrderUuid(), orderEntity);
  }

  public List<OrderEntity> getList() {
    log.debug("Получение списка заказов");
    return redissonClient.getMap(KEY).values()
        .stream()
        .filter(OrderEntity.class::isInstance)
        .map(OrderEntity.class::cast)
        .toList();
  }

  public void clear() {
    log.debug("Очищение статистики");
    redissonClient.getMap(KEY).clear();
  }
}
