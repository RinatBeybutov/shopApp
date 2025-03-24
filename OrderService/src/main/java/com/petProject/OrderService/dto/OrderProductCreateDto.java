package com.petProject.OrderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderProductCreateDto {
    @Schema(description = "Идентификатор продукта", defaultValue = "1")
    private int productId;
    @Schema(description = "Количество продуктов", defaultValue = "1")
    private int count;
}
