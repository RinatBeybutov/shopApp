package com.petProject.OrderService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductViewDto {

    private Integer id;

    private UUID uuid;

    private String name;

    private Integer categoryId;
}
