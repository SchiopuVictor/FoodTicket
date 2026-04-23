package foodticket.foodticket.controller;

import foodticket.foodticket.dtos.OrderItemRequest;
import foodticket.foodticket.dtos.OrderItemResponse;
import foodticket.foodticket.entity.PaymentMethod;
import foodticket.foodticket.mapper.OrderItemMapper;
import foodticket.foodticket.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemResponse> createOrderItem(
            @Valid @RequestBody OrderItemRequest request) {
        return ResponseEntity
                .ok(OrderItemMapper
                        .toDto(orderItemService
                                .createOrderItem(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getOrderItem(@PathVariable Long id) {
        return ResponseEntity
                .ok(OrderItemMapper
                        .toDto(orderItemService
                                .getOrderItem(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(
            @PathVariable Long id,
            @Valid @RequestBody OrderItemRequest request) {
        return ResponseEntity
                .ok(OrderItemMapper
                        .toDto(orderItemService
                                .updateOrderItem(id, request)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-finalize")
    public ResponseEntity<String> addProductAndFinalize(
            @RequestParam Long order_id, @RequestParam Long product_id,
            @RequestParam Integer quantity, @RequestParam PaymentMethod paymentMethod) {
        orderItemService.addProductAndFinalize(order_id, product_id, quantity, paymentMethod);
        return ResponseEntity.ok("Comanda finalizata!!");
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        byte[] pdf = orderItemService.createPdf(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=ticket.pdf")
                .body(pdf);

    }


    @GetMapping("/{id}/view")
    public ResponseEntity<byte[]> previewPdf(@PathVariable Long id) {
        byte[] pdf = orderItemService.createPdf(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline;filename=ticket.pdf")
                .body(pdf);
    }


}
