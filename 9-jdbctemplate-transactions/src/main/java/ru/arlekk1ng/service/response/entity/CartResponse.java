package ru.arlekk1ng.service.response.entity;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private long cartId;
    private String promoCode;
    private List<CartProductResponse> cartProducts;
}
