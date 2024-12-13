package com.petProject.OrderService.controller;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petProject.OrderService.config.API.PRODUCTS_API;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCTS_API)
@Tag(name = "Продукты", description = "API для работы с продуктами")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Получение всех продуктов")
    public ResponseEntity<List<ProductViewDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    @Operation(summary = "Создание продукта")
    public ResponseEntity<ProductViewDto> createProduct(@RequestBody ProductCreateDto dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    @GetMapping("category/{categoryId}")
    @Operation(summary = "Получение всех продуктов по категории")
    public ResponseEntity<List<ProductViewDto>> getProductsByCategoryId(@PathVariable
                                                                        @Parameter(description = "Идентификатор категории")
                                                                        Integer categoryId) {
        return ResponseEntity.ok(productService.getAllByCategoryId(categoryId));
    }

    /*@GetMapping("order/{orderId}")
    @Operation(summary = "Получение всех продуктов по заказу")
    public ResponseEntity<List<ProductWithCountViewDto>> getProductsInOrder(@PathVariable
                                                                            @Parameter(description = "Идентификатор заказа")
                                                                            Integer orderId) {
        return ResponseEntity.ok(productService.getProductsInOrder(orderId));
    }*/
}
