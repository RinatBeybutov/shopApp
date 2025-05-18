package com.petProject.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфиг для рест запросов
 */
@Configuration
public class RestConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
