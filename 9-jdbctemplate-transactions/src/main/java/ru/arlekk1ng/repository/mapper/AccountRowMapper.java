package ru.arlekk1ng.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.arlekk1ng.entity.Account;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        int clientId = rs.getInt("client_id");
        double balance = rs.getDouble("balance");
        return new Account(clientId, BigDecimal.valueOf(balance));
    }
}
