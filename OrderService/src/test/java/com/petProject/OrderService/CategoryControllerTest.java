package com.petProject.OrderService;

import com.petProject.OrderService.dto.CategoryViewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.petProject.OrderService.CategoryControllerTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование CategoryController")
class CategoryControllerTest extends DbTestContainersConfiguration {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UrlComponent urlComponent;

    @Test
    @DisplayName("Проверка получения списка объектов")
    void shouldGetAllObjects() {
        var response = restTemplate
                .getForEntity(getCategoriesUri(), CategoryViewDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        var categories = response.getBody();
        assertNotNull(categories);
        assertEquals(4, categories.length);

        assertThat(categories[0])
                .usingRecursiveComparison()
                .isEqualTo(getViewCategoryDto());
    }

    @Test
    @DisplayName("Проверка создания объекта")
    void shouldCreateCategory() {
        var response = restTemplate
                .postForEntity(
                        getCategoriesUri(),
                        getCreateCategoryDto(),
                        CategoryViewDto.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        var category = response.getBody();
        assertNotNull(category);
        assertThat(category)
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
                .isEqualTo(getCreatedCategoryDto());
    }

    private URI getCategoriesUri() {
        return UriComponentsBuilder
                .fromHttpUrl(urlComponent.getBaseUrl())
                .path(CATEGORIES_API_URL)
                .build()
                .toUri();
    }


}
