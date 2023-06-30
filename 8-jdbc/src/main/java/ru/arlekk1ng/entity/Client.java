package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private long cartId;
}
