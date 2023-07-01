package ru.arlekk1ng.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.arlekk1ng.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String promoCode = rs.getString("promo_code");
        return new Cart(id, promoCode);
    }
}
