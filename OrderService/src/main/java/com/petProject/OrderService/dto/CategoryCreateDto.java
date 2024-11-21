package com.petProject.OrderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сущность категории")
public class CategoryCreateDto {

    @Schema(description = "Название категории")
    private String name;
}
