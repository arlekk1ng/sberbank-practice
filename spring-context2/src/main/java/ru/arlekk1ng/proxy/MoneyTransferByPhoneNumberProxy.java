package ru.arlekk1ng.proxy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.arlekk1ng.model.MoneyTransfer;

@Primary
@Component
public class MoneyTransferByPhoneNumberProxy implements MoneyTransferProxy {

    @Override
    public void transfer(MoneyTransfer moneyTransfer) {
        System.out.println("Клиент банка: " + moneyTransfer.getFrom() + " совершил перевод ПО НОМЕРУ ТЕЛЕФОНА ("
                + moneyTransfer.getTo() + ") на сумму " + moneyTransfer.getSum() + " руб.");
    }
}
