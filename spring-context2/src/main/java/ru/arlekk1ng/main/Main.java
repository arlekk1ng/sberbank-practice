package ru.arlekk1ng.main;

import java.math.BigDecimal;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.arlekk1ng.config.ProjectConfig;
import ru.arlekk1ng.proxy.BankClientsProxy;
import ru.arlekk1ng.proxy.MoneyTransferByPhoneNumberProxy;
import ru.arlekk1ng.repository.DBBankClientsRepository;
import ru.arlekk1ng.repository.DBMoneyTransferRepository;
import ru.arlekk1ng.service.MoneyTransferService;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var moneyTransferService = context.getBean(MoneyTransferService.class);

        moneyTransferService.transfer("Ilya Bazhenov", "+79216832117", BigDecimal.valueOf(1000L));
    }

    public static void withoutSpring() {
        BankClientsProxy bankClientsProxy = new BankClientsProxy(new DBBankClientsRepository());
        var moneyTransferByPhoneNumberProxy = new MoneyTransferByPhoneNumberProxy();
        var dbMoneyTransferRepository = new DBMoneyTransferRepository();

        var moneyTransferService = new MoneyTransferService(
                bankClientsProxy, moneyTransferByPhoneNumberProxy, dbMoneyTransferRepository);

        moneyTransferService.transfer("Ilya Bazhenov", "+79216832117", BigDecimal.valueOf(1000L));
    }
}
