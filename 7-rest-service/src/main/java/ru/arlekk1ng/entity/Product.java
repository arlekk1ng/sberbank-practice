package ru.arlekk1ng.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private static long nextId = 1L;

    private long id;
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.id = getNextId();
        this.name = name;
        this.price = price;
    }

    private static long getNextId() {
        return nextId++;
    }
}
