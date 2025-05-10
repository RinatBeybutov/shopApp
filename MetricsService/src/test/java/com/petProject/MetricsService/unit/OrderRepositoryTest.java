package com.petProject.MetricsService.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.petProject.MetricsService.config.RedisContainerConfig;
import com.petProject.MetricsService.entity.redis.OrderEntity;
import com.petProject.MetricsService.repository.redis.OrderRepository;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(RedisContainerConfig.class)
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  @DisplayName("Проверка сохранения заказа")
  void test() {
    orderRepository.clear();

    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderUuid(UUID.fromString("5ca8cb6b-aaea-4a72-a46a-b12198ff3557"));
    orderEntity.setTime("12.00");
    orderRepository.save(orderEntity);

    List<OrderEntity> list = orderRepository.getList();
    assertEquals(1, list.size());
  }
}
