package com.petProject.OrderService.controller;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;
import com.petProject.OrderService.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petProject.OrderService.config.API.CATEGORIES_API;

@RestController
@RequestMapping(CATEGORIES_API)
@RequiredArgsConstructor
@Tag(name = "Категории", description = "API для работы с категориями")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Получение всех категорий")
    public ResponseEntity<List<CategoryViewDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping
    @Operation(summary = "Создание новой категории")
    public ResponseEntity<CategoryViewDto> create(@RequestBody CategoryCreateDto dto) {
       return ResponseEntity.ok(categoryService.create(dto));
    }
}
