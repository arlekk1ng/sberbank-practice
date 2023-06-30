package ru.arlekk1ng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBProductRepository implements ProductRepository, JDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Product product) {
        String insertSql = "INSERT INTO PRODUCTS (name, price, count) VALUES (?, ?, ?);";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getCount());
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Product> findById(long productId) {
        String selectSql = "SELECT * FROM PRODUCTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) productId);
            return preparedStatement;
        };

        RowMapper<Product> rowMapperProduct = getRowMapperProduct();

        List<Product> productList = jdbcTemplate.query(preparedStatementCreator, rowMapperProduct);

        return productList.stream().findFirst();
    }

    @Override
    public List<Product> findAll(String productName) {
        String selectSql = "SELECT * FROM PRODUCTS where name like ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "%" + (productName == null ? "" : productName) + "%");
            return preparedStatement;
        };
        
        RowMapper<Product> rowMapperProduct = getRowMapperProduct();

        return jdbcTemplate.query(preparedStatementCreator, rowMapperProduct);
    }

    @Override
    public boolean update(Product product) {
        String selectSql = "UPDATE PRODUCTS SET name = ?, price = ?, count = ? where id = ?;";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setInt(4, (int) product.getId());
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    @Override
    public boolean deleteById(long productId) {
        String selectSql = "DELETE FROM PRODUCTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) productId);
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    private static RowMapper<Product> getRowMapperProduct() {
        return (resultSet, rows) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int count = resultSet.getInt("count");

            return new Product(id, name, BigDecimal.valueOf(price), count);
        };
    }
}
