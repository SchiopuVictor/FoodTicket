package foodticket.foodticket.controller;

import foodticket.foodticket.dtos.ProductRequest;
import foodticket.foodticket.dtos.ProductResponse;
import foodticket.foodticket.mapper.ProductMapper;
import foodticket.foodticket.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request)
    {
       return ResponseEntity.ok(ProductMapper
               .toDto(productService.createProduct(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id)
    {
     return ResponseEntity.ok(ProductMapper
             .toDto(productService.getProduct(id)));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id, @RequestBody ProductRequest request)
    {
           return ResponseEntity.ok(ProductMapper
                   .toDto(productService.updateProduct(id, request)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
