package com.petProject.MetricsService.utility;

import com.petProject.MetricsService.entity.redis.OrderEntity;
import com.petProject.MetricsService.entity.redis.RedisStatsEntity;
import com.petProject.MetricsService.repository.redis.OrderRepository;
import com.petProject.MetricsService.repository.redis.RedisStatsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class RedisDBFiller {

    private final RedisStatsRepository redisStatsRepository;

    private final OrderRepository orderRepository;

    @PostConstruct
    private void init() {
        if (redisStatsRepository.getCountEntities() == 0) {
            redisStatsRepository.save(new RedisStatsEntity("19.00", 10));
            redisStatsRepository.save(new RedisStatsEntity("20.00", 5));
            redisStatsRepository.save(new RedisStatsEntity("21.00", 3));
        }

        redisStatsRepository.printAll();

        if(orderRepository.getList().isEmpty()) {
            orderRepository.save(getOneOrder());
        }
    }

    private static OrderEntity getOneOrder() {
        LocalDateTime now = LocalDateTime.now();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderUuid(UUID.randomUUID());
        orderEntity.setTime("%d.%d".formatted(now.getHour(), now.getMinute()));
        return orderEntity;
    }
}
