package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long productId) {
        boolean isDeleted = productRepository.deleteById(productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        boolean isUpdated = productRepository.update(product);
        if (isUpdated) {
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok().body(optionalProduct.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        return productRepository.findAll(name);
    }

    @PostMapping
    public long addProduct(@RequestParam String name, @RequestParam double price) {
        return productRepository.save(name, BigDecimal.valueOf(price));
    }
}
