package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Account;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.AccountRepository;
import ru.arlekk1ng.service.TransferService;

import java.net.URI;
import java.util.List;

@RestController
public class AccountController {
    private static final String URL = "http://localhost:8080";
    private TransferService transferService;
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(TransferService transferService, AccountRepository accountRepository) {
        this.transferService = transferService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("client/{clientId}/transfer")
    public ResponseEntity<?> transferMoney(@PathVariable long clientId) {
        boolean isTransferred = transferService.transferMoney(clientId);
        if (isTransferred) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("account")
    public ResponseEntity<?> register(@RequestBody Account account) {
        boolean isSaved = accountRepository.save(account);
        if (isSaved) {
            URI uri = URI.create(URL + "/account/" + account.getClientId());
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("account")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
