package ru.arlekk1ng.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.arlekk1ng.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String login = rs.getString("login");
        String password = rs.getString("password");
        int cartId = rs.getInt("cart_id");
        return new Client(id, name, email, login, password, cartId);
    }
}
