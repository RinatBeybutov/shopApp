package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;

import java.util.List;

public interface ProductService {

    List<ProductViewDto> getProducts();

    ProductViewDto create(ProductCreateDto dto);

    List<ProductViewDto> getAllByCategoryId(Integer categoryId);

    List<ProductWithCountViewDto> getProductsInOrder(Integer orderId);
}
