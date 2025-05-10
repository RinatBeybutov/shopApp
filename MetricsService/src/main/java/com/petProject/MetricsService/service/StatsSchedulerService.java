package com.petProject.MetricsService.service;

import com.petProject.MetricsService.entity.redis.RedisStatsEntity;
import com.petProject.MetricsService.entity.sql.SqlStatsEntity;
import com.petProject.MetricsService.repository.redis.OrderRepository;
import com.petProject.MetricsService.repository.redis.RedisStatsRepository;
import com.petProject.MetricsService.repository.sql.SqlStatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsSchedulerService {

    private final OrderRepository orderRepository;

    private final RedisStatsRepository redisStatsRepository;

    private final SqlStatsRepository sqlStatsRepository;

    private static final int OPEN_HOUR = 8;

    private static final int CLOSE_HOUR = 22;

    /**
     * Регулярная задача для сохранения статистики за день в sql
     */
    @Scheduled(cron = "${order.daily.cron}")
    public void saveStatsDaily() {
        RMap<String, RedisStatsEntity> allStatistics = redisStatsRepository.getAllStatistics();

        var sqlStatsEntities = allStatistics.entrySet()
                .stream()
                .map(StatsSchedulerService::convertToSqlEntity)
                .toList();

        sqlStatsRepository.saveAll(sqlStatsEntities);
        redisStatsRepository.clear();
    }

    /**
     * Регулярная задача для сохранения количества заказов каждый час в Redis
     */
    @Scheduled(cron = "${order.hourly.cron}")
    public void countOrdersHourly() {
        var now = LocalDateTime.now();
        int hour = now.getHour();

        if (hour > CLOSE_HOUR || hour <= OPEN_HOUR) {
            return;
        }

        var orders = orderRepository.getList();

        if (orders.isEmpty()) {
            log.info("Заказов за последний час не было..");
            return;
        }

        log.info("Количество заказов за последний час - {}", orders.size());

        var statisticEntity = createStatistic(orders.size(), hour);
        redisStatsRepository.save(statisticEntity);
        orderRepository.clear();
    }

    private static RedisStatsEntity createStatistic(int ordersSize, int hour) {
        RedisStatsEntity redisStatsEntity = new RedisStatsEntity();
        redisStatsEntity.setCount(ordersSize);
        redisStatsEntity.setTime(String.format("%d.00", hour));
        return redisStatsEntity;
    }

    private static SqlStatsEntity convertToSqlEntity(Map.Entry<String, RedisStatsEntity> entry) {
        RedisStatsEntity redisStats = entry.getValue();
        SqlStatsEntity entity = new SqlStatsEntity();
        entity.setTotalOrders(redisStats.getCount());
        int hour = Integer.parseInt(redisStats.getTime().substring(0, 2));
        var now = LocalDateTime.of(
                LocalDate.now().minusDays(1),
                LocalTime.of(hour, 0)
        );
        entity.setDate(now);
        return entity;
    }

}
