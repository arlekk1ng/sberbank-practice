package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Промежуточная сущность между корзиной и продуктами.
 * Является элементом списка для продуктовой корзины.
 */
@Data
@AllArgsConstructor
public class ProductCount {
    private Product product;
    private int count;
}
