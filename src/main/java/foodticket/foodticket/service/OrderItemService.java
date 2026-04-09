package foodticket.foodticket.service;

import foodticket.foodticket.dtos.OrderItemRequest;
import foodticket.foodticket.entity.*;
import foodticket.foodticket.exception.OrderItemNotFoundException;
import foodticket.foodticket.exception.OrderNotFoundException;
import foodticket.foodticket.exception.ProductNotFoundException;
import foodticket.foodticket.mapper.OrderItemMapper;
import foodticket.foodticket.repositpory.OrderItemRepository;
import foodticket.foodticket.repositpory.OrderRespository;
import foodticket.foodticket.repositpory.ProductRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderRespository orderRespository;
    private final ProductRespository productRespository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem createOrderItem(OrderItemRequest request) {

        Order order = orderRespository
                .findById(request.getOrder())
                .orElseThrow(OrderNotFoundException::new);

        Product product = productRespository
                .findById(request.getProduct())
                .orElseThrow(ProductNotFoundException::new);

        OrderItem orderItem = OrderItemMapper.toEntity(request);
        orderItem.setOrder(order);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setProduct(product);
        order.addItem(orderItem);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(Long id) {
        return orderItemRepository
                .findById(id)
                .orElseThrow(OrderItemNotFoundException::new);
    }

    @Transactional
    public OrderItem updateOrderItem(Long id, OrderItemRequest request) {
        orderRespository
                .findById(request.getOrder())
                .orElseThrow(OrderNotFoundException::new);

        Product product = productRespository
                .findById(request.getProduct())
                .orElseThrow(ProductNotFoundException::new);

        OrderItem orderItem = orderItemRepository
                .findById(id)
                .orElseThrow(OrderItemNotFoundException::new);

        orderItem.setProduct(product);
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(request.getQuantity());

        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository
                .findById(id)
                .orElseThrow(OrderItemNotFoundException::new);
        orderItemRepository.delete(orderItem);
    }

    @Transactional
    public void addProductAndFinalize(Long order_id, Long product_id, Integer quantity, PaymentMethod paymentMethod) {

        Order order = orderRespository
                .findById(order_id)
                .orElseThrow(OrderNotFoundException::new);

        Product product = productRespository
                .findById(product_id)
                .orElseThrow(ProductNotFoundException::new);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);

        order.addItem(item);
        order.calculateTotalAmount();
        order.setPaymentMethod(paymentMethod);
        order.setStatus(Status.FINALIZED);
        orderRespository.save(order);
    }

}
