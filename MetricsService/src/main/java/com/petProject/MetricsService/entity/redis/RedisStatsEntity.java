package com.petProject.MetricsService.entity.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Stat")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RedisStatsEntity implements Serializable {

    // Время в часах (19.00)
    private String time;

    // Количество заказов за час
    private Integer count;
}