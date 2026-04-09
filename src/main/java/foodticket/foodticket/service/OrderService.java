package foodticket.foodticket.service;

import foodticket.foodticket.dtos.OrderRequest;
import foodticket.foodticket.entity.Order;
import foodticket.foodticket.exception.OrderNotFoundException;
import foodticket.foodticket.mapper.OrderMapper;
import foodticket.foodticket.repositpory.OrderRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRespository orderRespository;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = OrderMapper.toEntity(request);
        order.setDateTime(LocalDateTime.now());
        order.setTotalAmount(order.calculateTotalAmount());
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
    // trebuie sa rezolv problema cu total amount!!!!

}
