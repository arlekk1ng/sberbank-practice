package ru.arlekk1ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    Optional<CartProduct> findCartProductByCartAndProduct(Cart cart, Product product);
    Optional<CartProduct> findCartProductByCartAndProduct_Id(Cart cart, long productId);
    List<CartProduct> findCartProductsByCart(Cart cart);
}
