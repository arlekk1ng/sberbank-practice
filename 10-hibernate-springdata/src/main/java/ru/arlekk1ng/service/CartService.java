package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);
    CartProduct addProduct(Cart cart, Product product);
    List<CartProduct> getProducts(Cart cart);
    boolean changeProductCount(Cart cart, long productId, int count);
    boolean deleteProduct(Cart cart, long productId);
}
