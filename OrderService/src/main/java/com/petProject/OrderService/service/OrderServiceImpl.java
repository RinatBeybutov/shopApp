package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderKafkaDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.dto.ProductWithCountViewDto;
import com.petProject.OrderService.entity.OrderEntity;
import com.petProject.OrderService.entity.ProductToOrderEntity;
import com.petProject.OrderService.mapper.OrderMapper;
import com.petProject.OrderService.mapper.OrderProductMapper;
import com.petProject.OrderService.mapper.ProductMapper;
import com.petProject.OrderService.repository.OrderRepository;
import com.petProject.OrderService.repository.ProductRepository;
import com.petProject.OrderService.repository.ProductToOrderRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductToOrderRepository productToOrderRepository;

    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    private final OrderProductMapper orderProductMapper;

    private final ProductMapper productMapper;

    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public List<OrderViewDto> getOrders(UUID userUuid) {
        return orderRepository.findAllByUserUuid(userUuid)
                .stream()
                .map(orderEntity -> orderMapper.toDto(orderEntity, getProducts(orderEntity)))
                .toList();
    }

    @Override
    @Transactional
    public OrderViewDto create(OrderCreateDto createDto) {
        var orderEntity = orderMapper.toEntity(createDto);
        orderEntity.setCreatedAt(LocalDate.now());
        orderEntity = orderRepository.save(orderEntity);
        var productToOrderEntities = getProductToOrderEntities(createDto, orderEntity);
        var productsViewDtos = saveAndMapProductToOrders(productToOrderEntities);
        sendOrderToKafka(orderEntity.getUuid());
        return orderMapper.toDto(orderEntity, productsViewDtos);
    }

    @Override
    @Transactional
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
        productToOrderRepository.deleteAllByOrderId(orderId);
    }

    private void fillProductAndOrder(List<ProductToOrderEntity> orderedProducts) {
        orderedProducts.forEach(productToOrderEntity -> {
            productToOrderEntity.setOrder(orderRepository.getById(productToOrderEntity.getOrderId()));
            productToOrderEntity.setProduct(productRepository.getById(productToOrderEntity.getProductId()));
        });
    }

    private List<ProductWithCountViewDto> getProducts(OrderEntity orderEntity) {
        return productToOrderRepository.findAllByOrderId(orderEntity.getId())
                .stream()
                .map(productMapper::toCountDto)
                .toList();
    }

    private List<ProductWithCountViewDto> saveAndMapProductToOrders(List<ProductToOrderEntity> productToOrderEntities) {
        return productToOrderRepository.saveAll(productToOrderEntities)
                .stream()
                .map(productMapper::toCountDto)
                .toList();
    }

    private List<ProductToOrderEntity> getProductToOrderEntities(OrderCreateDto createDto, OrderEntity order) {
        var orderId = order.getId();
        var productToOrderEntities = createDto.getProducts()
                .stream()
                .map(productOrderDto ->
                        orderProductMapper.toEntity(productOrderDto, orderId))
                .toList();

        fillProductAndOrder(productToOrderEntities);
        return productToOrderEntities;
    }

    private void sendOrderToKafka(UUID orderUuid) {
        int hour = LocalDateTime.now().getHour();
        String time = hour < 10 ? "0%s.00".formatted(hour)
            : "%s.00".formatted(hour);
        OrderKafkaDto orderDto = new OrderKafkaDto(orderUuid, time);
        kafkaProducer.sendMessage(orderDto);
    }
}
