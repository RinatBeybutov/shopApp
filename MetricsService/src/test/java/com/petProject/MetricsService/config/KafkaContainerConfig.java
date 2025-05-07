package com.petProject.MetricsService.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class KafkaContainerConfig {

    static {
        KafkaContainer kafka = new KafkaContainer(
                DockerImageName.parse("confluentinc/cp-kafka:7.6.1")
        );

        kafka.start();

        System.setProperty("spring.kafka.bootstrap-servers", kafka.getBootstrapServers());
        System.setProperty("spring.kafka.consumer.auto-offset-reset", "earliest");
    }
}
