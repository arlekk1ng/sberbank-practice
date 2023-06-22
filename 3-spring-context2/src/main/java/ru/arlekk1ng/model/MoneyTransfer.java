package ru.arlekk1ng.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MoneyTransfer {
    private String from;
    private String to;
    private BigDecimal sum;
    private LocalDate date;

    public MoneyTransfer(String from, String to, BigDecimal sum) {
        this.from = from;
        this.to = to;
        this.sum = sum;
        this.date = LocalDate.now();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }
}
