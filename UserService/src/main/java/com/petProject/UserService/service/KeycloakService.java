package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserCreateDto;
import java.util.UUID;

/**
 * Сеервис для взаимодействия с keycloak
 */
public interface KeycloakService {

  void save(UserCreateDto userCreateDto, UUID uuid);

  void delete(UUID uuid);
}
