package foodticket.foodticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product", schema = "foodticket")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "category")
    private String category;

    @Column(name = "available")
    private Boolean available = false;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}