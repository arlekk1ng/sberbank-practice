package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.repository.CartProductRepository;
import ru.arlekk1ng.repository.CartRepository;
import ru.arlekk1ng.response.CartResponse;
import ru.arlekk1ng.service.CartResponseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {
    private static final String URL = "http://localhost:8080";
    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;
    private CartResponseService cartResponseService;

    @Autowired
    public CartController(
            CartRepository cartRepository,
            CartProductRepository cartProductRepository,
            CartResponseService cartResponseService)
    {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartResponseService = cartResponseService;
    }

    @PatchMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody CartProduct requestCartProduct) {
        Optional<CartProduct> optionalCartProduct
                = cartProductRepository.find(requestCartProduct.getCartId(), requestCartProduct.getProductId());

        if (optionalCartProduct.isPresent()) {
            CartProduct cartProduct = optionalCartProduct.get();
            cartProduct.setProductCount(cartProduct.getProductCount() + 1);
            boolean isUpdated = cartProductRepository.update(cartProduct);
            if (isUpdated) {
                return ResponseEntity.accepted().body(cartProduct);
            }
            return ResponseEntity.notFound().build();
        }

        requestCartProduct.setProductCount(1);
        long cartProductId = cartProductRepository.save(requestCartProduct);
        URI uri = URI.create(URL + "/cartproduct/" + cartProductId);
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/change")
    public ResponseEntity<?> changeProductCount(@RequestBody CartProduct requestCartProduct) {
        Optional<CartProduct> optionalCartProduct
                = cartProductRepository.find(requestCartProduct.getCartId(), requestCartProduct.getProductId());

        if (optionalCartProduct.isPresent()) {
            CartProduct cartProduct = optionalCartProduct.get();
            cartProduct.setProductCount(requestCartProduct.getProductCount());
            boolean isUpdated = cartProductRepository.update(cartProduct);
            if (isUpdated) {
                return ResponseEntity.accepted().body(cartProduct);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody CartProduct requestCartProduct) {
        Optional<CartProduct> optionalCartProduct
                = cartProductRepository.find(requestCartProduct.getCartId(), requestCartProduct.getProductId());

        if (optionalCartProduct.isPresent()) {
            CartProduct cartProduct = optionalCartProduct.get();
            boolean isDeleted = cartProductRepository.deleteById(cartProduct.getId());
            if (isDeleted) {
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody Cart cart) {
        long cartId = cartRepository.save(cart);
        URI uri = URI.create(URL + "/cart/" + cartId);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            CartResponse cartResponse
                    = cartResponseService.getCartResponseFromCart(optionalCart.get());
            return ResponseEntity.ok().body(cartResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Cart> geCarts() {
        return cartRepository.findAll();
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody Cart cart) {
        boolean isUpdated = cartRepository.update(cart);
        if (isUpdated) {
            return ResponseEntity.accepted().body(cart);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable long cartId) {
        boolean isDeleted = cartRepository.deleteById(cartId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
