package ru.arlekk1ng.proxy;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.repository.BankClientsRepository;

@Repository
public class BankClientsProxy {
    private final BankClientsRepository bankClientsRepository;

    public BankClientsProxy(BankClientsRepository bankClientsRepository) {
        this.bankClientsRepository = bankClientsRepository;
    }

    public boolean isBankClient(String name) {
        List<String> bankClients = bankClientsRepository.getBankClients();
        return bankClients.contains(name);
    }
}
