package com.petProject.UserService.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Дто для отправки в keycloak при создании пользователя
 */
@Getter
@Setter
@Builder
public class UserKeycloakCreateDto {

  private String id;

  private String username;

  private String firstName;

  private String lastName;

  private String email;

  private Boolean emailVerified;

  private Boolean enabled;

  private Boolean verified;

  private List<CredentionalKeycloakDto> credentials;
}
