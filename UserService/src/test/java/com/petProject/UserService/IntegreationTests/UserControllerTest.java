package com.petProject.UserService.IntegreationTests;

import com.petProject.UserService.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.petProject.UserService.IntegreationTests.UserControllerTestData.getViewUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование UserController")
public class UserControllerTest extends DbTestContainersConfiguration {

    private static final int USER_ID = 1;

    private static final String USER_API_URL = "/api/user-service/users/{id}";

    private static final String USERS_API_URL = "/api/user-service/users";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UrlComponent urlComponent;


    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Проверка получения списка объектов")
    void shouldGetAllObjects() {
        var response = restTemplate
                .getForEntity(getUsersUri(), UserDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserDto[] users = response.getBody();
        assertNotNull(users);
        assertEquals(2, users.length);

        assertThat(users[0])
                .usingRecursiveComparison()
                .isEqualTo(getViewUserDto());
    }

    @Test
    @DisplayName("Проверка получения объекта")
    void shouldGetObject() {
        var response = restTemplate.getForEntity(
                getUserUri(USER_ID),
                UserDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .isEqualTo(getViewUserDto());
    }

    private URI getUserUri(int userId) {
        return UriComponentsBuilder
                .fromHttpUrl(urlComponent.getBaseUrl(port))
                .path(USER_API_URL)
                .build(userId);
    }

    private URI getUsersUri() {
        return UriComponentsBuilder
                .fromHttpUrl(urlComponent.getBaseUrl(port))
                .path(USERS_API_URL)
                .build()
                .toUri();
    }
}
