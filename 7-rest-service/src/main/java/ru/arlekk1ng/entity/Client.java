package ru.arlekk1ng.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Client {
    private static long nextId = 1L;

    private final long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private ProductCart cart;

    public Client(String name, String login, String password, String email, ProductCart cart) {
        this.id = getNextId();
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.cart = cart;
    }

    public String getPassword() {
        return "NOT AVAILABLE";
    }

    private static long getNextId() {
        return nextId++;
    }
}
