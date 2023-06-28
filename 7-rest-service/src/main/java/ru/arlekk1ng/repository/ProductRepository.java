package ru.arlekk1ng.repository;

import java.util.List;
import java.util.Optional;
import ru.arlekk1ng.entity.Product;

public interface ProductRepository {

    long save(Product product);
    boolean update(Product product);
    boolean deleteById(long id);
    Optional<Product> findById(long id);
    List<Product> findAll(String name);
}
