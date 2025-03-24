package com.petProject.UserService.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Конфигурация для запуска тестов с использованием контейнеров Postgres
 */
@TestConfiguration
public class PostgresContainerConfiguration {

  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("testDb")
          .withUsername("test")
          .withPassword("test");

  static {
    postgreSQLContainer.start();

    /**
     * spring.flyway.user=postgres
     * spring.flyway.password=postgres
     */
    System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
    System.setProperty("spring.flyway.url", postgreSQLContainer.getJdbcUrl());
    System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
    System.setProperty("spring.flyway.password", postgreSQLContainer.getPassword());
    System.setProperty("spring.flyway.user", postgreSQLContainer.getUsername());
  }
}
