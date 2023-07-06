package ru.arlekk1ng.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_cars")
@Data
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private BigDecimal balance;
}
