package ru.arlekk1ng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private long clientId;
    private BigDecimal balance;
}
