package foodticket.foodticket.dtos;


import foodticket.foodticket.entity.PaymentMethod;
import foodticket.foodticket.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private LocalDateTime dateTime;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private Status status;
    private String cashierName;

}
