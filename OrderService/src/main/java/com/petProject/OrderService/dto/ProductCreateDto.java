package com.petProject.OrderService.dto;

import lombok.Data;

@Data
public class ProductCreateDto {
    private String name;
    private Integer categoryId;
}
