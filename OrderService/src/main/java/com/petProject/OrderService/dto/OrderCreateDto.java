package com.petProject.OrderService.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OrderCreateDto {
    private LocalDate createdAt;
    private UUID userUuid;
    private List<OrderProductCreateDto> products;
}
