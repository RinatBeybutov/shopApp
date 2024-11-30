package com.petProject.OrderService;

import com.petProject.OrderService.dto.OrderViewDto;

import java.time.LocalDate;
import java.util.UUID;

public class OrderControllerTestData {

    public static final String ORDERS_API_URL = "/orders";

    public static OrderViewDto getViewOrderDto() {
        OrderViewDto orderViewDtoDto = new OrderViewDto();
        orderViewDtoDto.setId(1);
        orderViewDtoDto.setUuid(UUID.fromString("36c4caec-48ba-4099-abc6-cf80026905d6"));
        orderViewDtoDto.setCreatedAt(LocalDate.of(2024, 9,30));
        orderViewDtoDto.setUserUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"));
        return orderViewDtoDto;
    }
}
