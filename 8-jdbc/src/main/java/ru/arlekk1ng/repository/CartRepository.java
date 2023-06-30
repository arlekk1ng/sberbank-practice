package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    long save(Cart cart);
    Optional<Cart> findById(long cartId);
    List<Cart> findAll();
    boolean update(Cart cart);
    boolean deleteById(long cartId);
}
