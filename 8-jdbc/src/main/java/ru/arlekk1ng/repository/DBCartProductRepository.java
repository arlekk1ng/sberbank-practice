package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCartProductRepository implements CartProductRepository, JDBCRepository {

    @Override
    public long save(CartProduct cartProduct) {
        String insertSql = "INSERT INTO CARTS_PRODUCTS VALUES (DEFAULT, ?, ?, ?);";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement
                        = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, (int) cartProduct.getCartId());
            preparedStatement.setInt(2, (int) cartProduct.getProductId());
            preparedStatement.setInt(3, cartProduct.getProductCount());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            throw new RuntimeException("Ошибка при получении идентификатора CartProduct");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CartProduct> find(long cartId, long productId) {
        String selectSql = "SELECT * FROM CARTS_PRODUCTS where cart_id = ? and product_id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) cartId);
            preparedStatement.setInt(2, (int) productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int count = resultSet.getInt("count");

                CartProduct cartProduct = new CartProduct(id, cartId, productId, count);
                return Optional.of(cartProduct);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CartProduct cartProduct) {
        String selectSql = "UPDATE CARTS_PRODUCTS SET count = ? where id = ?;";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, cartProduct.getProductCount());
            preparedStatement.setInt(2, (int) cartProduct.getId());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long productCountId) {
        String selectSql = "DELETE FROM CARTS_PRODUCTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) productCountId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
