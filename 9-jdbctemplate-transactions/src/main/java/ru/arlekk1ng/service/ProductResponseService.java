package ru.arlekk1ng.service;

import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.response.ProductResponse;

@Service
public class ProductResponseService {

    public ProductResponse getProductResponseFromProduct(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
