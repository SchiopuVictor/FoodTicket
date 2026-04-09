package foodticket.foodticket.mapper;

import foodticket.foodticket.dtos.OrderItemRequest;
import foodticket.foodticket.dtos.OrderItemResponse;
import foodticket.foodticket.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItemResponse toDto(OrderItem request) {
        return OrderItemResponse.builder()
                .cashierName(request.getOrder().getCashierName())
                .price(request.getPrice())
                .productName(request.getProduct().getName())
                .quantity(request.getQuantity())
                .subtotal(request.getSubtotal())
                .build();
    }

    public static OrderItem toEntity(OrderItemRequest request) {
        return OrderItem.builder()
                .quantity(request.getQuantity())
                .build();
    }

}
