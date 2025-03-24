package com.petProject.UserService.integreationTests;

import static com.petProject.UserService.data.UserControllerTestData.createdViewDto;
import static com.petProject.UserService.data.UserControllerTestData.getCreateUserDto;
import static com.petProject.UserService.data.UserControllerTestData.getViewUserDto;
import static com.petProject.UserService.config.API.USER_API;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.petProject.UserService.configuration.PostgresContainerConfiguration;
import com.petProject.UserService.dto.UserViewDto;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * Тесты для UserController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование UserController")
@Import(PostgresContainerConfiguration.class)
class UserControllerTest {

  private static final UUID USER_ID = UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293");

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Проверка получения списка объектов")
  void shouldGetAllObjects() {
    var response = restTemplate
        .getForEntity(USER_API, UserViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    UserViewDto[] users = response.getBody();
    assertNotNull(users);
    assertEquals(5, users.length);

    assertThat(users[0])
        .usingRecursiveComparison()
        .isEqualTo(getViewUserDto());
  }

  @Test
  @DisplayName("Проверка получения объекта")
  void shouldGetObject() {
    var response = restTemplate.getForEntity(
        USER_API + "/" + USER_ID,
        UserViewDto.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertThat(response.getBody())
        .usingRecursiveComparison()
        .isEqualTo(getViewUserDto());
  }

  @Test
  @DisplayName("Проверка создания объекта")
  void shouldCreateObject() {
    var response = restTemplate.postForEntity(USER_API,
                                              getCreateUserDto(),
                                              UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var user = response.getBody();
    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(createdViewDto());

    restTemplate.exchange(USER_API + "/" + user.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }
}
