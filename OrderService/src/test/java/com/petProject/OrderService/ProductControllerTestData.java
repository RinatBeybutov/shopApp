package com.petProject.OrderService;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;

import java.util.UUID;

public class ProductControllerTestData {

    public static final String PRODUCTS_API_URL = "/products";

    public static ProductViewDto getViewProductDto() {
        ProductViewDto productViewDto = new ProductViewDto();
        productViewDto.setId(1);
        productViewDto.setName("Coca-cola");
        productViewDto.setUuid(UUID.fromString("c4ec28c3-2369-4709-9b44-355d3e2d5640"));
        productViewDto.setCategoryId(1);
        return productViewDto;
    }

    public static ProductCreateDto getCreateProductDto() {
        ProductCreateDto product = new ProductCreateDto();
        product.setName("Pepsi");
        product.setCategoryId(2);
        return product;
    }

    public static ProductViewDto getCreatedProductDto() {
        ProductViewDto productViewDto = new ProductViewDto();
        productViewDto.setName("Pepsi");
        productViewDto.setCategoryId(2);
        return productViewDto;
    }

}
