package com.petProject.MetricsService.service;

import com.petProject.MetricsService.dto.MetricsDto;
import com.petProject.MetricsService.dto.MetricsDtoHourly;
import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.StatisticRepository;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticRepository statisticRepository;

    public MetricsDto getTotal() {
        RMap<Object, Object> allStatistics = statisticRepository.getAllStatistics();
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

    private MetricsDtoHourly mapToMetricDto(Entry<Object, Object> entry) {
        MetricsDtoHourly metricsDtoHourly = new MetricsDtoHourly();
        metricsDtoHourly.setHour(entry.getKey().toString());
        StatisticEntity stat = (StatisticEntity) entry.getValue();
        metricsDtoHourly.setOrderCount(stat.getCount());
        return metricsDtoHourly;
    }
}
