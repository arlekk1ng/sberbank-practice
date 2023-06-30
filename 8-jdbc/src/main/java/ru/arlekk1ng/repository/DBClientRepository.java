package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.Client;

import java.sql.*;
import java.util.Optional;

@Repository
public class DBClientRepository implements ClientRepository, JDBCRepository {

    @Override
    public long save(Client client) {
        String insertSql
                = "INSERT INTO CLIENTS VALUES (DEFAULT, ?, ?, ?, ?, ?);";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement
                        = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getLogin());
            preparedStatement.setString(4, client.getPassword());
            preparedStatement.setInt(5, (int) client.getCartId());

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
    public Optional<Client> findById(long clientId) {
        String selectSql = "SELECT * FROM CLIENTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) clientId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int cartId = resultSet.getInt("cart_id");

                Client client = new Client(id, name, email, login, password, cartId);
                return Optional.of(client);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long clientId) {
        String selectSql = "DELETE FROM CLIENTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) clientId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
