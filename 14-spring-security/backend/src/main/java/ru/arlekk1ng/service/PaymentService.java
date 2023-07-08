package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.Cart;

public interface PaymentService {

    boolean pay(Cart cart, String cardNumber);
}
