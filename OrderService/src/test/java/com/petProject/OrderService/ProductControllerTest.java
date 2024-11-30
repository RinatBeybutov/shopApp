package com.petProject.OrderService;

import com.petProject.OrderService.dto.ProductViewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.petProject.OrderService.ProductControllerTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование ProductController")
class ProductControllerTest extends DbTestContainersConfiguration {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UrlComponent urlComponent;

    @Test
    @DisplayName("Проверка получения списка объектов")
    void shouldGetAllObjects() {
        var response = restTemplate
                .getForEntity(getProductsUri(), ProductViewDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ProductViewDto[] products = response.getBody();
        assertNotNull(products);
        assertEquals(3, products.length);

        assertThat(products[0])
                .usingRecursiveComparison()
                .isEqualTo(getViewProductDto());
    }

    @Test
    @DisplayName("Проверка создания объекта")
    void shouldCreateCategory() {
        var response = restTemplate
                .postForEntity(
                        getProductsUri(),
                        getCreateProductDto(),
                        ProductViewDto.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        var category = response.getBody();
        assertNotNull(category);
        assertThat(category)
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
                .isEqualTo(getCreatedProductDto());
    }

    //TODO: Написать тест на фильтрацию продуктов по категориям

    private URI getProductsUri() {
        return UriComponentsBuilder
                .fromHttpUrl(urlComponent.getBaseUrl())
                .path(PRODUCTS_API_URL)
                .build()
                .toUri();
    }
}
