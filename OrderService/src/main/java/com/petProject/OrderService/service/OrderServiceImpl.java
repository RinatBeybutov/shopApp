package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.mapper.OrderMapper;
import com.petProject.OrderService.mapper.OrderProductMapper;
import com.petProject.OrderService.repository.OrderRepository;
import com.petProject.OrderService.repository.ProductToOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductToOrderRepository productToOrderRepository;

    private final OrderMapper orderMapper;

    private final OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    public List<OrderViewDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderViewDto create(OrderCreateDto dto) {
        var entity = orderMapper.toEntity(dto);
        entity = orderRepository.save(entity);
        var orderId = entity.getId();
        var orderedProducts = dto.getProducts()
                .stream()
                .map(productOrderDto ->
                        orderProductMapper.toEntity(productOrderDto, orderId))
                .toList();

        productToOrderRepository.saveAll(orderedProducts);

        return orderMapper.toDto(entity);
    }

    @Override
    @Transactional
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
        productToOrderRepository.deleteAllByOrderId(orderId);
    }
}
