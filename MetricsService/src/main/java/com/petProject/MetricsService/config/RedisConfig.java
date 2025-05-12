package com.petProject.MetricsService.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Конфигурация для подключения редиса
 */
@Configuration
@Profile("dev")
public class RedisConfig {

    @Value("${redis.host}")
    private String REDIS_HOST;

    @Value("${redis.port}")
    private String REDIS_PORT;

    private String REDIS_URL = "redis://%s:%s";

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_URL.formatted(REDIS_HOST, REDIS_PORT));
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
