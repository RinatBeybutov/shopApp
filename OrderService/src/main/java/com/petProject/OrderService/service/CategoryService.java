package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;

import java.util.List;

public interface CategoryService {

    List<CategoryViewDto> getCategories();

    CategoryViewDto create(CategoryCreateDto dto);
}
