package ru.arlekk1ng.response;

import lombok.Data;

@Data
public class ClientResponse {
    private long id;
    private String name;
    private CartResponse cart;
}
