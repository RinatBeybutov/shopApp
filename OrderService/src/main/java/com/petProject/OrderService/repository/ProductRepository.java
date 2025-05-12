package com.petProject.OrderService.repository;

import com.petProject.OrderService.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    List<ProductEntity> findAllByCategoryId(Integer categoryId);
}
