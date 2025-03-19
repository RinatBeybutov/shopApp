package com.petProject.MetricsService.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class OrderKafkaDto {
  private UUID orderUuid;
  private String time;
}
