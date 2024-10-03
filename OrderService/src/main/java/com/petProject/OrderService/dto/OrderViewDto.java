package com.petProject.OrderService.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class OrderViewDto {

    private Integer id;

    private UUID uuid;

    private LocalDate createdAt;

    private UUID userUuid;
}
