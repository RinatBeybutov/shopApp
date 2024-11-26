package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.ProductToOrderEntity;
import com.petProject.OrderService.mapper.ProductMapper;
import com.petProject.OrderService.repository.CategoryRepository;
import com.petProject.OrderService.repository.ProductRepository;
import com.petProject.OrderService.repository.ProductToOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductToOrderRepository productToOrderRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductViewDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductViewDto create(ProductCreateDto dto) {
        var categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Not found category with id = " + dto.getCategoryId()));

        var entity = productMapper.toEntity(dto);
        entity.setCategory(categoryEntity);

        entity = productRepository.save(entity);

        return productMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductViewDto> getAllByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductWithCountViewDto> getProductsInOrder(Integer orderId) {
        return productToOrderRepository.findAllByOrderId(orderId)
                .stream()
                .map(productMapper::toCountDto)
                .toList();
    }
}
