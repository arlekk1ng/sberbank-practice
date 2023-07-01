package ru.arlekk1ng.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.arlekk1ng.entity.CartProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartProductRowMapper implements RowMapper<CartProduct> {

    @Override
    public CartProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        int cartId = rs.getInt("cart_id");
        int productId = rs.getInt("product_id");
        int count = rs.getInt("count");
        return new CartProduct(id, cartId, productId, count);
    }
}
