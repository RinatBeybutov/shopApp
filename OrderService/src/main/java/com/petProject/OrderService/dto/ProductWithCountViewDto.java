package com.petProject.OrderService.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductWithCountViewDto extends ProductViewDto {
    private int count;
}
