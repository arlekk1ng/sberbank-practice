package ru.arlekk1ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.CartProductRepository;
import ru.arlekk1ng.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public CartProduct addProduct(Cart cart, Product product) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct(cart, product);

        CartProduct cartProduct;
        if (cartProductOptional.isPresent()) {
            cartProduct = cartProductOptional.get();
            cartProduct.setProductCount(cartProduct.getProductCount() + 1);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setProductCount(1);
        }

        return cartProductRepository.save(cartProduct);
    }

    @Override
    public List<CartProduct> getProducts(Cart cart) {
        return cartProductRepository.findCartProductsByCart(cart);
    }

    @Override
    public boolean changeProductCount(Cart cart, long productId, int count) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct_Id(cart, productId);

        if (cartProductOptional.isEmpty()) {
            return false;
        }

        CartProduct cartProduct = cartProductOptional.get();
        cartProduct.setProductCount(count);
        cartProductRepository.save(cartProduct);
        return true;
    }

    @Override
    public boolean deleteProduct(Cart cart, long productId) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct_Id(cart, productId);

        if (cartProductOptional.isEmpty()) {
            return false;
        }

        CartProduct cartProduct = cartProductOptional.get();
        cartProductRepository.deleteById(cartProduct.getId());
        return true;
    }
}
