package com.petProject.OrderService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryViewDto {

    private Integer id;
    private UUID uuid;
    private String name;
}
