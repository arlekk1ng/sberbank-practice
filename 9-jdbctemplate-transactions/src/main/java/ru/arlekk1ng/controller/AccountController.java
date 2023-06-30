package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arlekk1ng.service.TransferService;

@RestController
public class AccountController {
    private TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("client/{clientId}/transfer")
    public ResponseEntity<?> transferMoney(@PathVariable long clientId) {
        boolean isTransferred = transferService.transferMoney(clientId);
        if (isTransferred) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
