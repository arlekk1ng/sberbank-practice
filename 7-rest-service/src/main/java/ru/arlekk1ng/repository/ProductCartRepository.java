package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.ProductCart;

import java.util.List;
import java.util.Optional;

public interface ProductCartRepository {

    long save(ProductCart productCart);
    Optional<ProductCart> findById(long productCartId);
    List<ProductCart> findAll();
}
