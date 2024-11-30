package com.petProject.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
@Slf4j
public class DbTestContainersConfiguration extends AbstractContainer {

    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("testDb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    public DbTestContainersConfiguration() {
        postgreSQLContainer.start();
        var port = postgreSQLContainer.getMappedPort(5432);
        log.info("testcontainers started on: " + port);
    }
}
