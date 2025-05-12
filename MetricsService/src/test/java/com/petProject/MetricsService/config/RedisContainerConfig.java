package com.petProject.MetricsService.config;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class RedisContainerConfig {

  static {
    var redis = new RedisContainer(DockerImageName.parse("redis:6.2.6"))
            .withExposedPorts(6379);
    redis.start();

    System.setProperty("redis.host", redis.getHost());
    System.setProperty("redis.port", redis.getMappedPort(6379).toString());
  }
}
