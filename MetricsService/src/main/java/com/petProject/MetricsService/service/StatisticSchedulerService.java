package com.petProject.MetricsService.service;

import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.OrderRepository;
import com.petProject.MetricsService.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticSchedulerService {

    private final OrderRepository orderRepository;

    private final StatisticRepository statisticRepository;

    private static final int OPEN_HOUR = 8;

    private static final int CLOSE_HOUR = 22;

    @Scheduled(cron = "${order.hourly.cron}")
    public void countOrdersHourly() {
        var now = LocalDateTime.now();
        int hour = now.getHour();

        if(hour > CLOSE_HOUR || hour <= OPEN_HOUR) {
            return;
        }

        var orders = orderRepository.getList();

        if(orders.isEmpty()) {
            log.info("Заказов за последний час не было..");
            return;
        }

        log.info("Количество заказов за последний час - {}", orders.size());

        var statisticEntity = createStatistic(orders.size(), hour);
        statisticRepository.save(statisticEntity);
        orderRepository.clear();
    }

    private static StatisticEntity createStatistic(int ordersSize, int hour) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setCount(ordersSize);
        statisticEntity.setTime(String.format("%d.00", hour));
        return statisticEntity;
    }
}
