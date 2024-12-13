package com.petProject.OrderService.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OrderViewDto {

    private Integer id;

    private UUID uuid;

    private LocalDate createdAt;

    private UUID userUuid;

    private List<ProductWithCountViewDto> products;
}
