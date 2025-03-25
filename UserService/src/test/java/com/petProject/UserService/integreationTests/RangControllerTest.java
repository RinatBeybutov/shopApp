package com.petProject.UserService.integreationTests;

import static com.petProject.UserService.config.API.USER_API;
import static com.petProject.UserService.data.UserControllerTestData.createdViewDto;
import static com.petProject.UserService.data.UserControllerTestData.getCreateUserDto;
import static com.petProject.UserService.data.UserControllerTestData.getViewUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.petProject.UserService.config.API;
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
import org.springframework.http.ResponseEntity;

/**
 * Тесты для RangController
 *
 * @author Rinat B
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование RangController")
@Import(PostgresContainerConfiguration.class)
class RangControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Проверка увеличения ранга пользователя")
  void shouldAddRang() {
    // Создание пользователя
    var response = restTemplate.postForEntity(USER_API,
                                              getCreateUserDto(),
                                              UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var user = response.getBody();
    UUID userUuid = user.getUuid();

    // Увеличение ранга пользователя
    ResponseEntity<Void> response1 = restTemplate.postForEntity("/api/v1/rangs/" + userUuid,
                                                               null,
                                                               Void.class);
    assertNotNull(response1);
    assertEquals(HttpStatus.OK, response1.getStatusCode());

    // Проверка изменений
    var response3 = restTemplate.getForEntity(
        USER_API + "/" + userUuid,
        UserViewDto.class);
    assertEquals(HttpStatus.OK, response3.getStatusCode());
    assertNotNull(response3.getBody());
    assertThat(response3.getBody().getOrderCount())
        .isEqualTo(1);

    // Удаление пользователя
    restTemplate.exchange(USER_API + "/" + userUuid,
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }
}
