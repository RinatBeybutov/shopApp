package com.petProject.UserService.controller;

import static com.petProject.UserService.config.API.RANGS_API;

import com.petProject.UserService.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(RANGS_API)
@RequiredArgsConstructor
public class RangController {

  private final UserService userService;

  @PostMapping("/{userUuid}")
  public ResponseEntity<Void> addRang(@PathVariable("userUuid") UUID userId) {
    System.out.println("Увеличен ранг пользователя " + userId);
    userService.increaseOrderCount(userId);
    return ResponseEntity.ok().build();
  }
}
