package com.petProject.OrderService.repository;

import com.petProject.OrderService.entity.ProductToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductToOrderRepository extends JpaRepository<ProductToOrderEntity, Integer> {
    void deleteAllByOrderId(Integer orderId);

    List<ProductToOrderEntity> findAllByOrderId(Integer orderId);
}
