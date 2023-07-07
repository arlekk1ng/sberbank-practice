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
    public CartServiceImpl(
            CartRepository cartRepository,
            CartProductRepository cartProductRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public CartProduct addProductInCart(Cart cart, Product product) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct(cart, product);

        CartProduct cartProduct;
        if (cartProductOptional.isPresent()) {
            cartProduct = cartProductOptional.get();
            cartProduct.setProductCountInCart(cartProduct.getProductCountInCart() + 1);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setProductCountInCart(1);
        }

        return cartProductRepository.save(cartProduct);
    }

    @Override
    public List<CartProduct> getProductsInCart(Cart cart) {
        return cartProductRepository.findCartProductsByCart(cart);
    }

    @Override
    public boolean changeProductCountInCart(Cart cart, long productId, int newCountInCart) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct_Id(cart, productId);

        if (cartProductOptional.isEmpty()) {
            return false;
        }

        CartProduct cartProduct = cartProductOptional.get();
        cartProduct.setProductCountInCart(newCountInCart);
        cartProductRepository.save(cartProduct);
        return true;
    }

    @Override
    public boolean deleteProductInCart(Cart cart, long productId) {
        Optional<CartProduct> cartProductOptional
                = cartProductRepository.findCartProductByCartAndProduct_Id(cart, productId);

        if (cartProductOptional.isEmpty()) {
            return false;
        }

        CartProduct cartProduct = cartProductOptional.get();
        cartProductRepository.deleteById(cartProduct.getId());
        return true;
    }

    @Override
    public void deleteAllProductsInCart(Cart cart) {
        for (CartProduct cartProduct: getProductsInCart(cart)) {
            cartProductRepository.deleteById(cartProduct.getId());
        }
    }
}
