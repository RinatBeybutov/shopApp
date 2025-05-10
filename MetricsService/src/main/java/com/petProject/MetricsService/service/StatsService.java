package com.petProject.MetricsService.service;

import com.petProject.MetricsService.dto.MetricsDto;
import com.petProject.MetricsService.dto.MetricsDtoHourly;
import com.petProject.MetricsService.entity.redis.RedisStatsEntity;
import com.petProject.MetricsService.repository.redis.RedisStatsRepository;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final RedisStatsRepository redisStatsRepository;

    public MetricsDto getTotal() {
        var allStatistics = redisStatsRepository.getAllStatistics();
        int totalOrders = 0;
        List<MetricsDtoHourly> hourly = new ArrayList<>();
        for(var entry : allStatistics.entrySet()) {
            var metricsDtoHourly = mapToMetricDto(entry);
            hourly.add(metricsDtoHourly);
            totalOrders += metricsDtoHourly.getOrderCount();
        }
        MetricsDto metricsDto = new MetricsDto();
        metricsDto.setTotalOrders(totalOrders);
        metricsDto.setHourly(hourly);
        return metricsDto;
    }

    private MetricsDtoHourly mapToMetricDto(Entry<String, RedisStatsEntity> entry) {
        MetricsDtoHourly metricsDtoHourly = new MetricsDtoHourly();
        metricsDtoHourly.setHour(entry.getKey());
        metricsDtoHourly.setOrderCount(entry.getValue().getCount());
        return metricsDtoHourly;
    }
}
