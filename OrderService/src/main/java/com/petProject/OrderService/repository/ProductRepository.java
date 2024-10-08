package com.petProject.OrderService.repository;

import com.petProject.OrderService.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByCategoryId(Integer categoryId);
}
