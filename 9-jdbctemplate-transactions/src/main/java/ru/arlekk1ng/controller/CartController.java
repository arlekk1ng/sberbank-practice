package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.repository.CartProductRepository;
import ru.arlekk1ng.repository.CartRepository;
import ru.arlekk1ng.service.response.entity.CartResponse;
import ru.arlekk1ng.service.response.CartResponseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carts")
public class CartController {
    private static final String URL = "http://localhost:8080";
    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;
    private CartResponseService cartResponseService;

    @Autowired
    public CartController(
            CartRepository cartRepository,
            CartProductRepository cartProductRepository,
            CartResponseService cartResponseService
    ) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartResponseService = cartResponseService;
    }

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable long cartId, @PathVariable long productId) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.find(cartId, productId);

        CartProduct cartProduct;
        if (cartProductOptional.isPresent()) {
            cartProduct = cartProductOptional.get();
            cartProduct.setProductCount(cartProduct.getProductCount() + 1);
            cartProductRepository.update(cartProduct);
        } else {
            // проверка на существование корзины и продутка

            cartProduct = new CartProduct(cartId, productId, 1);
            long cartProductId = cartProductRepository.save(cartProduct);
            cartProduct.setId(cartProductId);
        }

        return ResponseEntity.accepted().body(cartProduct);
    }

    @PatchMapping("/{cartId}/products/{productId}")
    public ResponseEntity<?> changeProductCount(
            @PathVariable long cartId, @PathVariable long productId, @RequestBody CartProduct requestCartProduct) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.find(cartId, productId);

        if (cartProductOptional.isPresent()) {
            CartProduct cartProduct = cartProductOptional.get();
            cartProduct.setProductCount(requestCartProduct.getProductCount());
            cartProductRepository.update(cartProduct);
            return ResponseEntity.accepted().body(cartProduct);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long cartId, @PathVariable long productId) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.find(cartId, productId);

        if (cartProductOptional.isPresent()) {
            CartProduct cartProduct = cartProductOptional.get();
            cartProductRepository.deleteById(cartProduct.getId());
            return ResponseEntity.noContent().build();
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
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            CartResponse cartResponse
                    = cartResponseService.getCartResponseFromCart(cartOptional.get());
            return ResponseEntity.ok().body(cartResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> updateCart(@PathVariable long cartId, @RequestBody Cart cart) {
        cart.setId(cartId);
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
