package com.petProject.UserService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Дто токена для отправки запросов в keycloak
 */
@Getter
@Setter
public class TokenDto {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Integer expiresIn;

  @JsonProperty("refresh_token")
  private String refreshToken;
}