package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.CartRepository;
import ru.arlekk1ng.repository.ClientRepository;
import ru.arlekk1ng.service.response.entity.ClientResponse;
import ru.arlekk1ng.service.response.ClientResponseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final String URL = "http://localhost:8080";
    private ClientRepository clientRepository;
    private CartRepository cartRepository;
    private ClientResponseService clientResponseService;

    @Autowired
    public ClientController(
            ClientRepository clientRepository,
            CartRepository cartRepository,
            ClientResponseService clientResponseService
    ) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
        this.clientResponseService = clientResponseService;
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
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            ClientResponse clientResponse
                    = clientResponseService.getClientResponseFromClient(clientOptional.get());
            return ResponseEntity.ok().body(clientResponse);
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
