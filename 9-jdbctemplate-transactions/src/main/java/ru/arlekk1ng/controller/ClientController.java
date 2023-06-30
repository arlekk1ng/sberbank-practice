package ru.arlekk1ng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.CartRepository;
import ru.arlekk1ng.repository.ClientRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final String URL = "http://localhost:8080";
    private ClientRepository clientRepository;
    private CartRepository cartRepository;

    public ClientController(ClientRepository clientRepository, CartRepository cartRepository) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Client client) {
        long cartId = cartRepository.save(new Cart());
        client.setCartId(cartId);

        long clientId = clientRepository.save(client);

        URI uri = URI.create(URL + "/client/" + clientId);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok().body(optionalClient.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable long clientId) {
        boolean isDeleted = clientRepository.deleteById(clientId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
