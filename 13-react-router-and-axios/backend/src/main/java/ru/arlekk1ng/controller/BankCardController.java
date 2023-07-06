package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.BankCard;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.BankCardRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bank-cards")
public class BankCardController {
    private final BankCardRepository bankCardRepository;

    @Autowired
    public BankCardController(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody BankCard bankCard) throws URISyntaxException {
        BankCard savedBankCard = bankCardRepository.save(bankCard);
        return ResponseEntity
                .created(new URI("http://localhost:8080/bank-cards/" + savedBankCard.getId()))
                .build();
    }

    @GetMapping
    public List<BankCard> getAll() {
        return bankCardRepository.findAll();
    }

    @PutMapping("/{bankCardId}")
    public ResponseEntity<?> update(@PathVariable long bankCardId, @RequestBody BankCard reqBankCard) {
        Optional<BankCard> bankCardOptional = bankCardRepository.findById(bankCardId);
        if (bankCardOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BankCard bankCard = bankCardOptional.get();
        bankCard.setBalance(reqBankCard.getBalance());
        bankCardRepository.save(bankCard);

        return ResponseEntity.ok().body(bankCard);
    }
}
