package foodticket.foodticket.dtos;

import foodticket.foodticket.entity.PaymentMethod;
import foodticket.foodticket.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private LocalDateTime dateTime;
    private PaymentMethod paymentMethod;
    private Status status;
    private String cashierName;

}
