package ru.arlekk1ng.service.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.repository.ProductRepository;
import ru.arlekk1ng.service.response.entity.CartProductResponse;
import ru.arlekk1ng.service.response.entity.ProductResponse;

@Service
public class CartProductResponseService {
    private ProductRepository productRepository;
    private ProductResponseService productResponseService;

    @Autowired
    public CartProductResponseService(
            ProductRepository productRepository,
            ProductResponseService productResponseService
    ) {
        this.productRepository = productRepository;
        this.productResponseService = productResponseService;
    }

    public CartProductResponse getCartProductResponseFromCartProduct(CartProduct cartProduct) {
        CartProductResponse cartProductResponse = new CartProductResponse();
        ProductResponse product = productResponseService.getProductResponseFromProduct(
                productRepository.findById(cartProduct.getProductId()).get()
        );
        cartProductResponse.setProduct(product);
        cartProductResponse.setProductCount(cartProduct.getProductCount());
        return cartProductResponse;
    }
}
