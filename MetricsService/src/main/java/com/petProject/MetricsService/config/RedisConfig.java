package com.petProject.MetricsService.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.host:localhost}")
    private String REDIS_HOST;

    @Value("${redis.port:6379}")
    private String REDIS_PORT;

    private String REDIS_URL = "redis://%s:%s";

    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_URL.formatted(REDIS_HOST, REDIS_PORT));
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
