package foodticket.foodticket.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> accesHandler(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            map.put(error.getDefaultMessage(), error.getDefaultMessage());
        });
        return map;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> productNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mesage", "Product Not Found"));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> orderNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Order Not Found!!"));

    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> orderItemNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Order Item Not Found!"));
    }


}
