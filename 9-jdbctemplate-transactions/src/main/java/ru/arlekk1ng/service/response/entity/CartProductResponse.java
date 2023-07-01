package ru.arlekk1ng.service.response.entity;

import lombok.Data;

@Data
public class CartProductResponse {
    private ProductResponse product;
    private int productCount;
}
