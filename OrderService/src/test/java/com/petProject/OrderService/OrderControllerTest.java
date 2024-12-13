package com.petProject.OrderService;

import com.petProject.OrderService.dto.OrderViewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.petProject.OrderService.OrderControllerTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование OrderController")
class OrderControllerTest extends DbTestContainersConfiguration{


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UrlComponent urlComponent;

    @Test
    @DisplayName("Проверка получения списка объектов")
    void shouldGetAllObjects() {
        var response = restTemplate
                .getForEntity(getOrdersUri(), OrderViewDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        OrderViewDto[] orders = response.getBody();
        assertNotNull(orders);
        assertEquals(1, orders.length);

        assertThat(orders[0])
                .usingRecursiveComparison()
                .isEqualTo(getViewOrderDto());
    }

    //TODO: Написать тест на создание и удаление объекта

    private URI getOrdersUri() {
        return UriComponentsBuilder
                .fromHttpUrl(urlComponent.getBaseUrl())
                .path(ORDERS_API_URL)
//                .queryParam("userUuid", USER_UUID)
                .buildAndExpand(USER_UUID)
//                .build()
                .toUri();
    }
}
