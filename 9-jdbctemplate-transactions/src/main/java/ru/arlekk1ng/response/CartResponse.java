package ru.arlekk1ng.response;

import lombok.Data;
import ru.arlekk1ng.entity.CartProduct;

import java.util.List;

@Data
public class CartResponse {
    private long cartId;
    private String promoCode;
    private List<CartProductResponse> cartProducts;
}
