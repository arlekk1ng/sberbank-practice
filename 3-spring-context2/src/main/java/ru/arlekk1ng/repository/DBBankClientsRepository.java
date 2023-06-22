package ru.arlekk1ng.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DBBankClientsRepository implements BankClientsRepository {

    public List<String> getBankClients() {
        return List.of("Ilya Bazhenov", "Kirill Zuev", "Mikhail Sobolev");
    }
}
