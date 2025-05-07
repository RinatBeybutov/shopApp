package com.petProject.MetricsService.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petProject.MetricsService.config.KafkaContainerConfig;
import com.petProject.MetricsService.config.KafkaTestConfig;
import com.petProject.MetricsService.dto.OrderKafkaDto;
import com.petProject.MetricsService.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@DisplayName("Тесты для получения сообщения из кафки")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = {KafkaContainerConfig.class, KafkaTestConfig.class})
@ActiveProfiles("test")
class KafkaConsumerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private RedissonClient redissonClient;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Тестирование получения сообщения из кафки")
    @Test
    void shouldConsumeMessageFromKafka() throws JsonProcessingException {
        var orderKafkaDto = new OrderKafkaDto(
                UUID.fromString("e44b07a7-edbb-425a-9b76-5669f2c355be"),
                "13:00"
        );

        var message = objectMapper.writeValueAsString(orderKafkaDto);

        kafkaTemplate.send("t.order", message);

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    Mockito.verify(orderService, Mockito.times(1))
                            .saveOrder(Mockito.any());
                });
    }
}
