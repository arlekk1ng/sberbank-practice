package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.repository.CartProductRepository;

import java.net.URI;

@RestController
@RequestMapping("cartproduct")
public class CartProductController {
    private static final String URL = "http://localhost:8080";
    private CartProductRepository cartProductRepository;

    public CartProductController(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    @PostMapping
    public ResponseEntity<?> addCartProduct(@RequestBody CartProduct cartProduct) {
        long cartId = cartProductRepository.save(cartProduct);
        URI uri = URI.create(URL + "/cartproduct/" + cartId);
        return ResponseEntity.created(uri).build();
    }
}
