package com.petProject.OrderService.controller;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductViewDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductViewDto> createProduct(@RequestBody ProductCreateDto dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductViewDto>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.getAllByCategoryId(categoryId));
    }
}
