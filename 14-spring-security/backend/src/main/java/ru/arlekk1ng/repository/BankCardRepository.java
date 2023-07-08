package ru.arlekk1ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.BankCard;

import java.util.Optional;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {

    Optional<BankCard> findBankCardByCardNumber(String cardNumber);
}
