package com.petProject.MetricsService.utility;

import com.petProject.MetricsService.entity.OrderEntity;
import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.OrderRepository;
import com.petProject.MetricsService.repository.StatisticRepository;
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

    private final StatisticRepository statisticRepository;

    private final OrderRepository orderRepository;

    @PostConstruct
    private void init() {
        if (statisticRepository.getCountEntities() == 0) {
            statisticRepository.save(new StatisticEntity("19.00", 10));
            statisticRepository.save(new StatisticEntity("20.00", 5));
            statisticRepository.save(new StatisticEntity("21.00", 3));
        }

        statisticRepository.printAll();

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
