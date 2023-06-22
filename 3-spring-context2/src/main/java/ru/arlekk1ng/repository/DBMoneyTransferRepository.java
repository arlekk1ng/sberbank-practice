package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.model.MoneyTransfer;

@Repository
public class DBMoneyTransferRepository implements MoneyTransferRepository {

    @Override
    public void writeMoneyTransfer(MoneyTransfer moneyTransfer) {
        System.out.println("Запись денежного перевода в базу данных..");
        System.out.println("Кому: " + moneyTransfer.getTo() + " на сумму "
                + moneyTransfer.getSum() + " руб.");
    }
}
