package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);
    CartProduct addProductInCart(Cart cart, Product product);
    List<CartProduct> getProductsInCart(Cart cart);
    boolean changeProductCountInCart(Cart cart, long productId, int newCountInCart);
    boolean deleteProductInCart(Cart cart, long productId);
    void deleteAllProductsInCart(Cart cart);
}
