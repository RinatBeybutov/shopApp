package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductFilterDto;
import com.petProject.OrderService.dto.ProductViewDto;

import java.util.List;

public interface ProductService {

    List<ProductViewDto> getProducts(ProductFilterDto filter);

    ProductViewDto create(ProductCreateDto dto);

    List<ProductViewDto> getAllByCategoryId(Integer categoryId);

}
