package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    long save(Product product);
    Optional<Product> findById(long productId);
    List<Product> findAll(String productName);
    boolean update(Product product);
    boolean deleteById(long productId);
}
