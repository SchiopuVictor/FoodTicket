package foodticket.foodticket.controller;

import foodticket.foodticket.dtos.OrderRequest;
import foodticket.foodticket.dtos.OrderResponse;
import foodticket.foodticket.mapper.OrderMapper;
import foodticket.foodticket.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final MapperBuilder mapperBuilder;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {
        return ResponseEntity
                .ok(OrderMapper
                        .toDto(orderService
                                .createOrder(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity
                .ok(OrderMapper
                        .toDto(orderService
                                .getOrder(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {
        return ResponseEntity
                .ok(OrderMapper
                        .toDto(orderService
                                .updateOrder(id, request)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(
                orderService.getAllOrders()
                        .stream()
                        .map(OrderMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

}
