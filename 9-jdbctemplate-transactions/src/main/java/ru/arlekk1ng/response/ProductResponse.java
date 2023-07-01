package ru.arlekk1ng.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private BigDecimal price;
}
