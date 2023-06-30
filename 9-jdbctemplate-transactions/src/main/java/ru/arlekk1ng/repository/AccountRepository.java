package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    boolean save(Account account);
    Optional<Account> findByClientId(long clientId);
    List<Account> findAll();
    boolean changeBalance(long clientId, BigDecimal balance);
}
