package foodticket.foodticket.service;

import foodticket.foodticket.dtos.ProductRequest;
import foodticket.foodticket.entity.Product;
import foodticket.foodticket.exception.ProductNotFoundException;
import foodticket.foodticket.mapper.ProductMapper;
import foodticket.foodticket.repositpory.ProductRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRespository productRespository;

    @Transactional
    public Product createProduct(ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        return productRespository.save(product);
    }

    public Product getProduct(Long id) {
        return productRespository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {

        Product product = productRespository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.setAvailable(request.getAvailable());
        product.setCategory(request.getCategory());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productRespository.save(product);

    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRespository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRespository.delete(product);
    }

    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }


}
