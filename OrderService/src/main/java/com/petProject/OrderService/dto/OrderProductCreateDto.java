package com.petProject.OrderService.dto;

import lombok.Data;

@Data
public class OrderProductCreateDto {
    private int productId;
    private int count;
}
