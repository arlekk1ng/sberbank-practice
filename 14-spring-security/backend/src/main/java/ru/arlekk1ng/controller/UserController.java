package ru.arlekk1ng.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.*;
import ru.arlekk1ng.service.CartService;
import ru.arlekk1ng.service.ProductService;
import ru.arlekk1ng.service.UserService;
import ru.arlekk1ng.service.PaymentService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class UserController {
    private final UserService userService;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final ProductService productService;

    @Autowired
    public UserController(
            UserService userService,
            CartService cartService,
            PaymentService paymentService,
            ProductService productService
    ) {
        this.userService = userService;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> registration(@RequestBody User user) throws URISyntaxException {
        User savedUser = userService.save(user);
        return ResponseEntity
                .created(new URI("http://localhost:8080/users/" + savedUser.getId()))
                .build();
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        User user = userOptional.get();
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        Cart userCart = userOptional.get().getCart();
        user.setCart(userCart);

        userService.update(user);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}/cart/payment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> pay(@PathVariable long userId, @RequestBody BankCard bankCard) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = userOptional.get().getCart();
        boolean isPaid = paymentService.pay(userCart, bankCard.getCardNumber());

        if (isPaid) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/{userId}/cart/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addProductInCart(
            @PathVariable long userId, @RequestBody Product product) throws URISyntaxException {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Product> productOptional = productService.findById(product.getId());
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Cart userCart = userOptional.get().getCart();
        cartService.addProductInCart(userCart, product);

        return ResponseEntity
                .created(new URI("http://localhost:8080/users/" + userId + "/cart/products/" + product.getId()))
                .build();
    }

    @GetMapping("/{userId}/cart/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getProductsInCart(@PathVariable long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = userOptional.get().getCart();
        List<CartProduct> userCartProducts = cartService.getProductsInCart(userCart);

        return ResponseEntity.ok().body(userCartProducts);
    }

    @PutMapping("/{userId}/cart/products/{productId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> changeProductCountInCart(
            @PathVariable long userId, @PathVariable long productId, @RequestBody CartProduct cartProduct) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = userOptional.get().getCart();
        boolean isUpdated
                = cartService.changeProductCountInCart(userCart, productId, cartProduct.getProductCountInCart());
        
        if (isUpdated) {
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}/cart/products/{productId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductInCart(@PathVariable long userId, @PathVariable long productId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = userOptional.get().getCart();
        boolean isUpdated = cartService.deleteProductInCart(userCart, productId);

        if (isUpdated) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}/cart/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAllProductsInCart(@PathVariable long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = userOptional.get().getCart();
        cartService.deleteAllProductsInCart(userCart);

        return ResponseEntity.noContent().build();
    }
}
