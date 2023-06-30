package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCartRepository implements CartRepository, JDBCRepository {

    @Override
    public long save(Cart cart) {
        String insertSql = "INSERT INTO CARTS (promo_code) VALUES (?);";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement
                        = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, cart.getPromoCode());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            throw new RuntimeException("Ошибка при получении идентификатора корзины");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cart> findById(long cartId) {
        String selectSql = "SELECT * FROM CARTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) cartId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String promoCode = resultSet.getString("promo_code");

                Cart cart = new Cart(id, promoCode);
                return Optional.of(cart);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> findAll() {
        String selectSql = "SELECT * FROM CARTS";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Cart cart;
            List<Cart> carts = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String promoCode = resultSet.getString("promo_code");

                cart = new Cart(id, promoCode);
                carts.add(cart);
            }

            return carts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Cart cart) {
        String selectSql = "UPDATE CARTS SET promo_code = ? where id = ?;";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setString(1, cart.getPromoCode());
            preparedStatement.setInt(2, (int) cart.getId());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long cartId) {
        String selectSql = "DELETE FROM CARTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) cartId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
