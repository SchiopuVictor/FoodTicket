package foodticket.foodticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items", schema = "foodticket")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public void setProduct(Product product) {
        this.product = product;
        if (product.getPrice() != null) {
            this.price = product.getPrice();
            calculateTotalPrice();
        }
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null && !quantity.equals(getQuantity())) {
            this.quantity = quantity;
            calculateTotalPrice();
        }
    }

    public void calculateTotalPrice() {
        if (this.price == null || this.quantity == null) {
            return;
        }
        this.subtotal = this.price.multiply(new BigDecimal(this.quantity));
    }
}