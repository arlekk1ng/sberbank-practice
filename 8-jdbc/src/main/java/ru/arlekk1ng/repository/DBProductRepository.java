package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBProductRepository implements ProductRepository, JDBCRepository {

    @Override
    public long save(Product product) {
        String insertSql = "INSERT INTO PRODUCTS (name, price, count) VALUES (?, ?, ?);";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement
                        = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getCount());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            throw new RuntimeException("Ошибка при получении идентификатора продукта");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(long productId) {
        String selectSql = "SELECT * FROM PRODUCTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int count = resultSet.getInt("count");

                Product product = new Product(id, name, BigDecimal.valueOf(price), count);
                return Optional.of(product);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll(String productName) {
        String selectSql = "SELECT * FROM PRODUCTS where name like ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setString(1, "%" + (productName == null ? "" : productName) + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            Product product;
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int count = resultSet.getInt("count");

                product = new Product(id, name, BigDecimal.valueOf(price), count);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Product product) {
        String selectSql = "UPDATE PRODUCTS SET name = ?, price = ?, count = ? where id = ?;";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setInt(4, (int) product.getId());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long productId) {
        String selectSql = "DELETE FROM PRODUCTS where id = ?";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setInt(1, (int) productId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
