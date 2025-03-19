package com.petProject.MetricsService.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petProject.MetricsService.dto.OrderKafkaDto;
import com.petProject.MetricsService.service.OrderService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

  private static final String orderTopic = "${order_topic.name}";
  private final ObjectMapper objectMapper;
  private final OrderService orderService;

  @KafkaListener(topics = orderTopic)
  public void consumeMessage(String message) {
    log.info("Received message: {}", message);
    extractOrder(message)
        .ifPresent(orderService::saveOrder);
  }

  private Optional<OrderKafkaDto> extractOrder(String message) {
    try {
      OrderKafkaDto orderKafkaDto = objectMapper.readValue(message, OrderKafkaDto.class);
      return Optional.of(orderKafkaDto);
    } catch (JsonProcessingException e) {
      log.error("Error parsing JSON: {}", e.getMessage());
    }
    return Optional.empty();
  }
}
