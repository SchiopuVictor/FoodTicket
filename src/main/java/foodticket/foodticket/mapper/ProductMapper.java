package foodticket.foodticket.mapper;

import foodticket.foodticket.dtos.ProductRequest;
import foodticket.foodticket.dtos.ProductResponse;
import foodticket.foodticket.entity.Product;

public class ProductMapper {

    public static ProductResponse toDto(Product request){
        return ProductResponse.builder()
                .name(request.getName())
                .price(request.getPrice())
                .available(request.getAvailable())
                .category(request.getCategory())
                .build();
    }

    public static Product toEntity(ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .available(request.getAvailable())
                .category(request.getCategory())
                .build();
    }



}
