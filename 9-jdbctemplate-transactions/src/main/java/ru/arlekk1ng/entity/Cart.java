package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {
    private long id;
    private String promoCode;

    public Cart() {
    }
}
