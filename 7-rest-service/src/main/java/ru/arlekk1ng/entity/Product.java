package ru.arlekk1ng.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
    private static long nextId = 1L;

    private long id;
    private String name;
    private BigDecimal price;
    private int count;

    public Product(String name, BigDecimal price, int count) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.count = count;
    }
}
