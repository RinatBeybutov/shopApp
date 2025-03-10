package com.petProject.UserService.IntegreationTests;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Конфигурация для запуска тестов с использованием контейнеров Postgres
 */
@Configuration
@Slf4j
public class DbTestContainersConfiguration {

  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("testDb")
          .withUsername("test")
          .withPassword("test");

  public DbTestContainersConfiguration() {
    postgreSQLContainer.start();
    var port = postgreSQLContainer.getMappedPort(5432);
    log.info("testcontainers started on: " + port);
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

    /*protected String getBaseUrl() {
        return String.format("http://localhost:%d/", port);
    }*/
}
