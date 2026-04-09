package foodticket.foodticket.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product not found exception!!");
        
    }
}
