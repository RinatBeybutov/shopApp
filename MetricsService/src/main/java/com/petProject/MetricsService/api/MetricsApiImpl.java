package com.petProject.MetricsService.api;

import com.petProject.MetricsService.controller.MetricsApi;
import com.petProject.MetricsService.dto.MetricsDto;
import com.petProject.MetricsService.dto.MetricsDtoHourly;
import com.petProject.MetricsService.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Component
@RequiredArgsConstructor
@RestController
public class MetricsApiImpl implements MetricsApi {

    private final StatisticsService service;

    @Override
    public ResponseEntity<MetricsDto> getMetrics() {
        return ResponseEntity.ok(getMetricsDto());
    }

    private static MetricsDto getMetricsDto() {
        MetricsDtoHourly metricsDtoHourly = new MetricsDtoHourly();
        metricsDtoHourly.setHour("12:00");
        metricsDtoHourly.setOrderCount(10);

        MetricsDto metricsDto = new MetricsDto();
        metricsDto.setTotalOrders(10);
        metricsDto.setHourly(List.of(metricsDtoHourly));
        return metricsDto;
    }
}
