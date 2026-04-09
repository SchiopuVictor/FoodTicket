package foodticket.foodticket.mapper;

import foodticket.foodticket.dtos.OrderRequest;
import foodticket.foodticket.dtos.OrderResponse;
import foodticket.foodticket.entity.Order;

public class OrderMapper {

    public static OrderResponse toDto(Order request) {
        return OrderResponse.builder()
                .cashierName(request.getCashierName())
                .dateTime(request.getDateTime())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .totalAmount(request.getTotalAmount())
                .build();
    }

    public static Order toEntity(OrderRequest request) {
        return Order.builder().cashierName(request.getCashierName())
                .dateTime(request.getDateTime())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .build();
    }
}
