package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProduct {
    private long id;
    private long cartId;
    private long productId;
    private int productCount;
}
