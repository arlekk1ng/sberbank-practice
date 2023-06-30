package ru.arlekk1ng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCartRepository implements CartRepository, JDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Cart cart) {
        String insertSql = "INSERT INTO CARTS (promo_code) VALUES (?);";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cart.getPromoCode());
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Cart> findById(long cartId) {
        String selectSql = "SELECT * FROM CARTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) cartId);
            return preparedStatement;
        };

        RowMapper<Cart> rowMapperCart = getRowMapperCart();

        List<Cart> cartList = jdbcTemplate.query(preparedStatementCreator, rowMapperCart);

        return cartList.stream().findFirst();
    }

    @Override
    public List<Cart> findAll() {
        String selectSql = "SELECT * FROM CARTS";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            return connection.prepareStatement(selectSql);
        };

        RowMapper<Cart> rowMapperCart = getRowMapperCart();

        return jdbcTemplate.query(preparedStatementCreator, rowMapperCart);
    }

    @Override
    public boolean update(Cart cart) {
        String selectSql = "UPDATE CARTS SET promo_code = ? where id = ?;";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, cart.getPromoCode());
            preparedStatement.setInt(2, (int) cart.getId());
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    @Override
    public boolean deleteById(long cartId) {
        String selectSql = "DELETE FROM CARTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) cartId);
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    private static RowMapper<Cart> getRowMapperCart() {
        return (resultSet, rows) -> {
            int id = resultSet.getInt("id");
            String promoCode = resultSet.getString("promo_code");
            return new Cart(id, promoCode);
        };
    }
}
