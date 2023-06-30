package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.CartProduct;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository {

    long save(CartProduct cartProduct);
    Optional<CartProduct> find(long cartId, long productId);
    List<CartProduct> findAllByCartId(long cartId);
    boolean update(CartProduct cartProduct);
    boolean deleteById(long productCountId);
}
