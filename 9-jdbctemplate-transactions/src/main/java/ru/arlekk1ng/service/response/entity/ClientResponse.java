package ru.arlekk1ng.service.response.entity;

import lombok.Data;

@Data
public class ClientResponse {
    private long id;
    private String name;
    private CartResponse cart;
}
