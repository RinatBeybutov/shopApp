package com.petProject.OrderService.controller;

import com.petProject.OrderService.dto.OrderCreateDto;
import com.petProject.OrderService.dto.OrderViewDto;
import com.petProject.OrderService.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.petProject.OrderService.config.API.ORDERS_API;

@RestController
@RequestMapping(ORDERS_API)
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "API для работы с заказами")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Получение всех заказов")
    public ResponseEntity<List<OrderViewDto>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @PostMapping
    @Operation(summary = "Создание заказа")
    public ResponseEntity<OrderViewDto> createOrder(@RequestBody OrderCreateDto dto) {
        return ResponseEntity.ok(orderService.create(dto));
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Удаление заказа")
    public ResponseEntity<Void> deleteOrder(@PathVariable
                                            @Parameter(description = "Идентификатор заказа")
                                            Integer orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok().build();
    }
}
