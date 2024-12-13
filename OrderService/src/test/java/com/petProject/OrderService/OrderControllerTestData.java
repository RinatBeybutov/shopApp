package com.petProject.OrderService;

import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderControllerTestData {

    public static final String ORDERS_API_URL = "/orders/{userUuid}";

    public static final String USER_UUID = "423bd97c-f1af-413c-9f62-18b4ab158293";

    public static OrderViewDto getViewOrderDto() {
        OrderViewDto orderViewDtoDto = new OrderViewDto();
        orderViewDtoDto.setId(1);
        orderViewDtoDto.setUuid(UUID.fromString("36c4caec-48ba-4099-abc6-cf80026905d6"));
        orderViewDtoDto.setCreatedAt(LocalDate.of(2024, 9,30));
        orderViewDtoDto.setUserUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"));
        List<ProductWithCountViewDto> products = List.of(
                getProductWithCountViewDto1(),
                getProductWithCountViewDto2()
        );
        orderViewDtoDto.setProducts(products);
        return orderViewDtoDto;
    }

    private static ProductWithCountViewDto getProductWithCountViewDto2() {
        ProductWithCountViewDto dto = new ProductWithCountViewDto();
        dto.setId(2);
        dto.setUuid(UUID.fromString("77ff09f2-28cd-4ec0-865b-2e4917433631"));
        dto.setCount(3);
        dto.setCategoryId(1);
        dto.setName("Вода");
        return dto;
    }

    private static ProductWithCountViewDto getProductWithCountViewDto1() {
        ProductWithCountViewDto dto = new ProductWithCountViewDto();
        dto.setId(1);
        dto.setUuid(UUID.fromString("c4ec28c3-2369-4709-9b44-355d3e2d5640"));
        dto.setCount(2);
        dto.setCategoryId(1);
        dto.setName("Coca-cola");
        return dto;
    }
}
