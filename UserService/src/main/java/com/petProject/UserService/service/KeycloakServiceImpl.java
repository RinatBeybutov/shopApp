package com.petProject.UserService.service;

import com.petProject.UserService.dto.CredentionalKeycloakDto;
import com.petProject.UserService.dto.TokenDto;
import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserKeycloakCreateDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Реализация сервиса для взаимодействия с keycloak
 */
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

  private final RestTemplate restTemplate;

  @Override
  public void save(UserCreateDto userCreateDto, UUID uuid) {
    String token = getToken();

    CredentionalKeycloakDto password = CredentionalKeycloakDto.builder()
        .type("password")
        .value(userCreateDto.getPassword())
        .temporary(false)
        .build();

    var keycloakCreateDto = UserKeycloakCreateDto.builder()
        .id(uuid.toString())
        .credentials(List.of(password))
        .email(userCreateDto.getEmail())
        .username(userCreateDto.getName())
        .firstName("First name")
        .lastName("Last name")
        .enabled(true)
        .emailVerified(true)
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", token);

    HttpEntity<UserKeycloakCreateDto> request = new HttpEntity<>(keycloakCreateDto, headers);

    String url = "http://localhost:8200/admin/realms/App/users";

    ResponseEntity<Object> response = restTemplate.exchange(
        url,
        HttpMethod.POST,
        request,
        Object.class);

    if (response.getStatusCode() != HttpStatus.CREATED) {
      throw new RuntimeException("Error with creating user in ketcloak");
    }
  }

  @Override
  public void delete(UUID uuid) {
    // Получаем Access Token (например, через метод getAccessToken())
    String accessToken = getToken();

    // Формируем заголовки
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", accessToken);

    // Формируем запрос
    HttpEntity<Object> entity = new HttpEntity<>(null, headers);

    // Отправляем DELETE-запрос на удаление пользователя
    restTemplate.exchange(
        "http://localhost:8200/admin/realms/App/users/" + uuid,
        HttpMethod.DELETE,
        entity,
        Void.class
    );
  }

  private String getToken() {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("client_id", "admin-cli");
    params.add("grant_type", "password");
    params.add("username", "admin");
    params.add("password", "admin");

    // Формируем заголовки
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Формируем запрос
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

    // Отправляем запрос
    ResponseEntity<TokenDto> response = restTemplate.exchange(
        "http://localhost:8200/realms/master/protocol/openid-connect/token",
        HttpMethod.POST,
        request,
        TokenDto.class
    );

    if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
      String accessToken = response.getBody().getAccessToken();
      return "Bearer " + accessToken;
    }

    throw new RuntimeException("Error with getting token");
  }
}
