package com.petProject.OrderService;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;

import java.util.UUID;

public class CategoryControllerTestData {

    public static final String CATEGORIES_API_URL = "/categories";

    public static CategoryViewDto getViewCategoryDto() {
        CategoryViewDto categoryViewDto = new CategoryViewDto();
        categoryViewDto.setId(1);
        categoryViewDto.setName("Напитки");
        categoryViewDto.setUuid(UUID.fromString("9fafcad1-350b-4c14-aad7-902c89608354"));
        return categoryViewDto;
    }

    public static CategoryCreateDto getCreateCategoryDto() {
        CategoryCreateDto categoryCreateDto = new CategoryCreateDto();
        categoryCreateDto.setName("Настойки");
        return categoryCreateDto;
    }

    public static CategoryViewDto getCreatedCategoryDto() {
        CategoryViewDto categoryViewDto = new CategoryViewDto();
        categoryViewDto.setName("Настойки");
        return categoryViewDto;
    }
}
