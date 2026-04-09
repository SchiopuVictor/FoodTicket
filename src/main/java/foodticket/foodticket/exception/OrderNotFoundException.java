package foodticket.foodticket.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Order Not Found!");
    }
}
