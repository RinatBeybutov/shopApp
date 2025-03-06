package com.petProject.MetricsService.service;

import com.petProject.MetricsService.dto.MetricsDto;
import com.petProject.MetricsService.dto.MetricsDtoHourly;
import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.StatisticRepository;
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
        for (Object key : allStatistics.keySet()) {
            MetricsDtoHourly metricsDtoHourly = new MetricsDtoHourly();
            metricsDtoHourly.setHour(key.toString());
            StatisticEntity stat = (StatisticEntity) allStatistics.get(key);
            metricsDtoHourly.setOrderCount(stat.getCount());
            hourly.add(metricsDtoHourly);
            totalOrders += stat.getCount();
        }
        MetricsDto metricsDto = new MetricsDto();
        metricsDto.setTotalOrders(totalOrders);
        metricsDto.setHourly(hourly);
        return metricsDto;
    }
}
