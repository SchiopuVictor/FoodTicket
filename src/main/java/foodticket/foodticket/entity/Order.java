package foodticket.foodticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders", schema = "foodticket")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "cashier_name")
    private String cashierName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.orderItems.add(item);
    }

    public BigDecimal calculateTotalAmount() {
        return this.totalAmount = this.orderItems
                .stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}