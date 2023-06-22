package ru.arlekk1ng.repository;

import ru.arlekk1ng.model.MoneyTransfer;

public interface MoneyTransferRepository {

    void writeMoneyTransfer(MoneyTransfer moneyTransfer);
}
