package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.ClientRepository;

import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable long clientId) {
        boolean isDeleted = clientRepository.deleteById(clientId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok().body(optionalClient.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public long registerClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
