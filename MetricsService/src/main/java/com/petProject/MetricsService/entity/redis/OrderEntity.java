package com.petProject.MetricsService.entity.redis;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Order")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity implements Serializable {

  //UUID заказа
  private UUID orderUuid;

  //Время заказа
  private String time;
}