package com.petProject.OrderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Сущность заказа")
public class OrderCreateDto {
    /*@Schema(description = "Дата заказа")
    private LocalDate createdAt;*/

    @Schema(description = "Идентификатор пользователя",
        example = "423bd97c-f1af-413c-9f62-18b4ab158293",
        defaultValue = "423bd97c-f1af-413c-9f62-18b4ab158293")
    private UUID userUuid;

    @Schema(description = "Идентификаторы продуктов с количеством")
    private List<OrderProductCreateDto> products;
}
