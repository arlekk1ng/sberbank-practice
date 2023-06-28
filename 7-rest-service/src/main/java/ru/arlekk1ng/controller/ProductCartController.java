package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.entity.ProductCart;
import ru.arlekk1ng.repository.ProductCartRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class ProductCartController {
    private ProductCartRepository productCartRepository;

    public ProductCartController(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    @PatchMapping("/{productCartId}")
    public ResponseEntity<ProductCart> updateProductCount(
            @PathVariable long productCartId,
            @RequestBody Product product,
            @RequestParam int count
    ) {
        Optional<ProductCart> optionalProductCart = productCartRepository.findById(productCartId);
        if (optionalProductCart.isPresent()) {
            ProductCart productCart = optionalProductCart.get();
            boolean isUpdated = productCart.updateProductCount(product, count);
            if (isUpdated) {
                return ResponseEntity.ok().body(productCart);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productCartId}/product/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable long productCartId,
            @PathVariable long productId
    ) {
        Optional<ProductCart> optionalProductCart = productCartRepository.findById(productCartId);
        if (optionalProductCart.isPresent()) {
            ProductCart productCart = optionalProductCart.get();
            boolean isDeleted = productCart.deleteByProductId(productId);
            if (isDeleted) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{productCartId}")
    public ResponseEntity<?> addProduct(
            @PathVariable long productCartId,
            @RequestBody Product product
    ) {
        Optional<ProductCart> optionalProductCart = productCartRepository.findById(productCartId);
        if (optionalProductCart.isPresent()) {
            ProductCart productCart = optionalProductCart.get();
            productCart.addProduct(product);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<ProductCart> getProductCarts() {
        return productCartRepository.findAll();
    }
}
