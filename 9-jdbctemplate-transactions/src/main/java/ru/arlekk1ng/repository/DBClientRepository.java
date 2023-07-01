package ru.arlekk1ng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.mapper.ClientRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class DBClientRepository implements ClientRepository, JDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Client client) {
        String insertSql
                = "INSERT INTO CLIENTS VALUES (DEFAULT, ?, ?, ?, ?, ?);";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getLogin());
            preparedStatement.setString(4, client.getPassword());
            preparedStatement.setInt(5, (int) client.getCartId());
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Client> findById(long clientId) {
        String selectSql = "SELECT * FROM CLIENTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) clientId);
            return preparedStatement;
        };

        List<Client> clientList = jdbcTemplate.query(preparedStatementCreator, new ClientRowMapper());

        return clientList.stream().findFirst();
    }

    @Override
    public List<Client> findAll() {
        String selectSql = "SELECT * FROM CLIENTS";

        return jdbcTemplate.query(selectSql, new ClientRowMapper());
    }

    @Override
    public boolean deleteById(long clientId) {
        String selectSql = "DELETE FROM CLIENTS where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, (int) clientId);
            return preparedStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
}
