package ru.arlekk1ng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.repository.mapper.CartProductRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCartProductRepository implements CartProductRepository, JDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBCartProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(CartProduct cartProduct) {
        String insertSql = "INSERT INTO CARTS_PRODUCTS VALUES (DEFAULT, ?, ?, ?);";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, (int) cartProduct.getCartId());
            preparedStatement.setInt(2, (int) cartProduct.getProductId());
            preparedStatement.setInt(3, cartProduct.getProductCount());
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<CartProduct> find(long cartId, long productId) {
        String selectSql = "SELECT * FROM CARTS_PRODUCTS where cart_id = ? and product_id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) cartId);
            preparedStatement.setInt(2, (int) productId);
            return preparedStatement;
        };

        List<CartProduct> cartProductList = jdbcTemplate.query(preparedStatementCreator, new CartProductRowMapper());

        return cartProductList.stream().findFirst();
    }

    @Override
    public List<CartProduct> findAllByCartId(long cartId) {
        String selectSql = "SELECT * FROM CARTS_PRODUCTS where cart_id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) cartId);
            return preparedStatement;
        };

        return jdbcTemplate.query(preparedStatementCreator, new CartProductRowMapper());
    }

    @Override
    public boolean update(CartProduct cartProduct) {
        String selectSql = "UPDATE CARTS_PRODUCTS SET count = ? where id = ?;";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, cartProduct.getProductCount());
            preparedStatement.setInt(2, (int) cartProduct.getId());
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

    @Override
    public boolean deleteById(long productCountId) {
        String selectSql = "DELETE FROM CARTS_PRODUCTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) productCountId);
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
}
