package ru.arlekk1ng.entity;

import java.util.List;
import lombok.Data;

@Data
public class Basket {
    private static long nextId = 1L;

    private long id;
    private List<Product> products;
    private String promoCode;

    public Basket(List<Product> products, String promoCode) {
        this.id = nextId++;
        this.products = products;
        this.promoCode = promoCode;
    }
}
