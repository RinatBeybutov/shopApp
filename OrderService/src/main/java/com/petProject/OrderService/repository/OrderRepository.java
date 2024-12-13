package com.petProject.OrderService.repository;

import com.petProject.OrderService.entity.OrderEntity;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserUuid(UUID userUuid);
}
