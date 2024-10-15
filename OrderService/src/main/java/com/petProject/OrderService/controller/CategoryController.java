package com.petProject.OrderService.controller;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;
import com.petProject.OrderService.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petProject.OrderService.config.API.CATEGORIES_API;

@RestController
@RequestMapping(CATEGORIES_API)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryViewDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryViewDto> create(@RequestBody CategoryCreateDto dto) {
       return ResponseEntity.ok(categoryService.create(dto));
    }
}
