package ru.arlekk1ng.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.arlekk1ng.entity.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int count = rs.getInt("count");
        return new Product(id, name, BigDecimal.valueOf(price), count);
    }
}
