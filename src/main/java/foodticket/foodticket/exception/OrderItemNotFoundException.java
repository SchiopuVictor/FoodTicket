package foodticket.foodticket.exception;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException() {
        super("Order Item not found exception!!");
    }
}
