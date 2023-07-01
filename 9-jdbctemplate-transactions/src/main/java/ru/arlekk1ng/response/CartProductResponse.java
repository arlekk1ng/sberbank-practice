package ru.arlekk1ng.response;

import lombok.Data;

@Data
public class CartProductResponse {
    private ProductResponse product;
    private int productCount;
}
