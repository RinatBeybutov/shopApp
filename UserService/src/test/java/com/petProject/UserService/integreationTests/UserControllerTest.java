package com.petProject.UserService.integreationTests;

import static com.petProject.UserService.config.API.USER_API;
import static com.petProject.UserService.data.UserControllerTestData.USER_NOT_FOUND_MESSAGE;
import static com.petProject.UserService.data.UserControllerTestData.WRONG_UUID;
import static com.petProject.UserService.data.UserControllerTestData.createdViewDto;
import static com.petProject.UserService.data.UserControllerTestData.getCreateUserDto;
import static com.petProject.UserService.data.UserControllerTestData.getViewUserDto;
import static com.petProject.UserService.data.UserControllerTestData.updatedUserDto;
import static com.petProject.UserService.data.UserControllerTestData.userUpdateDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.petProject.UserService.configuration.PostgresContainerConfiguration;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.service.KeycloakService;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * Тесты для UserController
 *
 * @author Rinat B
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование UserController")
@Import(PostgresContainerConfiguration.class)
class UserControllerTest {

  private static final UUID USER_ID = UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293");

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private KeycloakService keycloakService;

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
  @DisplayName("Проверка ошибки при получении пользователя")
  void testGetOneNotFound() {
    var response = restTemplate.getForEntity(USER_API + "/" + WRONG_UUID,
                                             String.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(USER_NOT_FOUND_MESSAGE.formatted(WRONG_UUID), response.getBody());
  }

  @Test
  @DisplayName("Проверка создания / удаления объекта")
  void shouldCreateObject() {
    var createResponse = restTemplate.postForEntity(USER_API,
                                              getCreateUserDto(),
                                              UserViewDto.class);

    assertEquals(HttpStatus.OK, createResponse.getStatusCode());
    assertNotNull(createResponse.getBody());
    var user = createResponse.getBody();

    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(createdViewDto());

    restTemplate.exchange(USER_API + "/" + user.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);

    var getResponse = restTemplate.getForEntity(USER_API + "/" + WRONG_UUID,
                                             String.class);

    assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
  }

  @Test
  @DisplayName("Проверка на обновление пользователя по UUID")
  void testUpdateSuccess() {
    var user = restTemplate.postForEntity(USER_API,
                                          getCreateUserDto(),
                                          UserViewDto.class)
        .getBody();

    assertNotNull(user);

    var uuid = user.getUuid();

    var newDto = userUpdateDto();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    var response = restTemplate.exchange(USER_API + "/" + uuid,
                                         HttpMethod.PUT,
                                         new HttpEntity<>(newDto, headers),
                                         UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    user = response.getBody();

    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(updatedUserDto());

    restTemplate.exchange(USER_API + "/" + uuid,
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }
}
