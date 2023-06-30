package ru.arlekk1ng.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Account;
import ru.arlekk1ng.repository.mapper.AccountRowMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class DBAccountRepository implements AccountRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Account> findByClientId(long clientId) {
        String sql = "SELECT * FROM accounts WHERE client_id = ?";
        Account account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), clientId);
        return Optional.of(account);
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    @Override
    public boolean changeBalance(long clientId, BigDecimal balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE client_id = ?";
        int rows = jdbcTemplate.update(sql, balance, clientId);
        return rows > 0;
    }
}
