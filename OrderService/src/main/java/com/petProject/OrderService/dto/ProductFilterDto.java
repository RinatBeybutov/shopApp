package com.petProject.OrderService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductFilterDto {

    private UUID categoryUuid;

    private String name; //products?name=iphone&categoryUuid=556566
}
