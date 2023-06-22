package ru.arlekk1ng.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.model.MoneyTransfer;
import ru.arlekk1ng.proxy.BankClientsProxy;
import ru.arlekk1ng.proxy.MoneyTransferProxy;
import ru.arlekk1ng.repository.MoneyTransferRepository;

@Service
public class MoneyTransferService {
    private final BankClientsProxy bankClientsProxy;
    private final MoneyTransferProxy moneyTransferProxy;
    private final MoneyTransferRepository moneyTransferRepository;

    public MoneyTransferService(BankClientsProxy bankClientsProxy, MoneyTransferProxy moneyTransferProxy,
                                MoneyTransferRepository moneyTransferRepository) {
        this.bankClientsProxy = bankClientsProxy;
        this.moneyTransferProxy = moneyTransferProxy;
        this.moneyTransferRepository = moneyTransferRepository;
    }

    public void transfer(String from, String to, BigDecimal sum) {
        if (!bankClientsProxy.isBankClient(from)) {
            System.out.println("Ошибка! " + from + " не является клиентом банка.");
            return;
        }

        MoneyTransfer moneyTransfer = new MoneyTransfer(from, to, sum);
        moneyTransferProxy.transfer(moneyTransfer);

        moneyTransferRepository.writeMoneyTransfer(moneyTransfer);
    }
}
