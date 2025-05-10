package com.petProject.MetricsService.api;

import com.petProject.MetricsService.controller.MetricsApiDelegate;
import com.petProject.MetricsService.dto.MetricsDto;
import com.petProject.MetricsService.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsApiImpl implements MetricsApiDelegate {

    private final StatsService service;

    @Override
    public ResponseEntity<MetricsDto> getMetrics() {
        return ResponseEntity.ok(service.getTotal());
    }
}
