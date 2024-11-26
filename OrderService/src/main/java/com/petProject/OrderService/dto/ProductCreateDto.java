package com.petProject.OrderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сущность продукта")
public class ProductCreateDto {
    @Schema(description = "Имя продукта")
    private String name;
    @Schema(description = "Категория продукта")
    private Integer categoryId;
}
