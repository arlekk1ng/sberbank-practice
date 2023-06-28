package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    long save(String name, BigDecimal price);
    Optional<Product> findById(long productId);
    List<Product> findAll(String name);
    boolean update(Product product);
    boolean deleteById(long productId);
}
