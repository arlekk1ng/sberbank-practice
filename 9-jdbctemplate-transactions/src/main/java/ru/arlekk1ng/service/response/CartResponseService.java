package ru.arlekk1ng.service.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.repository.CartProductRepository;
import ru.arlekk1ng.service.response.entity.CartProductResponse;
import ru.arlekk1ng.service.response.entity.CartResponse;

import java.util.List;

@Service
public class CartResponseService {
    private CartProductRepository cartProductRepository;
    private CartProductResponseService cartProductResponseService;

    @Autowired
    public CartResponseService(
            CartProductRepository cartProductRepository,
            CartProductResponseService cartProductResponseService
    ) {
        this.cartProductRepository = cartProductRepository;
        this.cartProductResponseService = cartProductResponseService;
    }

    public CartResponse getCartResponseFromCart(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getId());
        cartResponse.setPromoCode(cart.getPromoCode());

        List<CartProductResponse> cartProducts
                = cartProductRepository.findAllByCartId(cart.getId()).stream()
                .map(cartProduct -> cartProductResponseService.getCartProductResponseFromCartProduct(cartProduct))
                .toList();
        cartResponse.setCartProducts(cartProducts);

        return cartResponse;
    }
}
