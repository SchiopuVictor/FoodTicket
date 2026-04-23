package foodticket.foodticket.service;

import foodticket.foodticket.dtos.OrderItemRequest;
import foodticket.foodticket.dtos.OrderRequest;
import foodticket.foodticket.entity.Order;
import foodticket.foodticket.entity.OrderItem;
import foodticket.foodticket.entity.Product;
import foodticket.foodticket.exception.OrderNotFoundException;
import foodticket.foodticket.mapper.OrderMapper;
import foodticket.foodticket.repositpory.OrderRespository;
import foodticket.foodticket.repositpory.ProductRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRespository orderRespository;
    private final ProductRespository productRespository;


    @Transactional
    public Order createOrder(OrderRequest request) {

        Order order = OrderMapper.toEntity(request);
        for (OrderItemRequest item : request.getItemRequest()) {
            Product product = productRespository.findById(item.getProduct())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            order.addItem(orderItem);
        }
        order.setDateTime(request.getDateTime());
        order.calculateTotalAmount();

        return orderRespository.save(order);
    }


    public Order getOrder(Long id) {
        return orderRespository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public Order updateOrder(Long id, OrderRequest request) {

        Order order = orderRespository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        order.setStatus(request.getStatus());
        order.setCashierName(request.getCashierName());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setTotalAmount(order.calculateTotalAmount());

        return orderRespository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRespository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        orderRespository.delete(order);
    }


}
