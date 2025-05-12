package com.petProject.MetricsService.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderKafkaDto {
  private UUID orderUuid;
  private String time;
}
