package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(long id);
    boolean update(Product product);
    void deleteById(long id);
}
