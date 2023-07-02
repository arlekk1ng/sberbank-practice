package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.ProductRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    private static final String URL = "http://localhost:8080";
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        long productId = productRepository.save(product);
        URI uri = URI.create(URL + "/products/" + productId);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok().body(productOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        return productRepository.findAll(name);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable long productId, @RequestBody Product product) {
        product.setId(productId);
        boolean isUpdated = productRepository.update(product);
        if (isUpdated) {
            return ResponseEntity.accepted().body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long productId) {
        boolean isDeleted = productRepository.deleteById(productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
