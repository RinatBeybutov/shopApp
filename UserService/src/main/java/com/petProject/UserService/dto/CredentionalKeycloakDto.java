package com.petProject.UserService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Дто креденшенелов для создания пользователя
 */
@Getter
@Setter
@Builder
public class CredentionalKeycloakDto {

  private String type;

  private String value;

  private Boolean temporary;
}
