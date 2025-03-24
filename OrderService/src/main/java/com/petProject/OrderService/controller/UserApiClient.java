package com.petProject.OrderService.controller;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "UserService", url = "${user_service.api.base_url}")
public interface UserApiClient {

  @PostMapping(value = "api/v1/rangs/{userUuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Void> increaseRang(@PathVariable("userUuid") UUID userId);
}
