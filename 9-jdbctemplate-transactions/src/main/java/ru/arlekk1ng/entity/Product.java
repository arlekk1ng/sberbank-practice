package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private int count;
}
