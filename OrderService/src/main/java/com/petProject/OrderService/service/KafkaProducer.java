package com.petProject.OrderService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petProject.OrderService.dto.OrderKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

  @Value("${order_topic.name}")
  private String orderTopic;

  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(OrderKafkaDto orderKafkaDto) {
    try {
      String message = objectMapper.writeValueAsString(orderKafkaDto);
      kafkaTemplate.send(orderTopic, message);
      log.info("Order sent to kafka topic {}", orderKafkaDto);
    } catch (JsonProcessingException e) {
      log.error("Exception in sending kafka message {}", orderKafkaDto);
    }
  }
}
