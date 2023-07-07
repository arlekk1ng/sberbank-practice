package ru.arlekk1ng.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.arlekk1ng.entity.Cart;

@Data
@AllArgsConstructor
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private Cart cart;
}
