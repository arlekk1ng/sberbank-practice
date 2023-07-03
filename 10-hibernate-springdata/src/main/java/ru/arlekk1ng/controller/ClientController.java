package ru.arlekk1ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.*;
import ru.arlekk1ng.service.CartService;
import ru.arlekk1ng.service.ClientService;
import ru.arlekk1ng.service.PaymentService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;
    private final CartService cartService;
    private final PaymentService paymentService;
    
    @Autowired
    public ClientController(ClientService clientService, CartService cartService, PaymentService paymentService) {
        this.clientService = clientService;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientService.save(client);
        return ResponseEntity
                .created(new URI("http://localhost:8080/clients/" + savedClient.getId()))
                .build();
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clientOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Client client) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        client.setId(id);
        client.setCart(clientCart);

        clientService.update(client);

        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}/cart/payment")
    public ResponseEntity<Void> pay(@PathVariable long clientId, @RequestBody BankCard bankCard) {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        boolean isPaid = paymentService.pay(clientCart, bankCard.getCardNumber());

        if (!isPaid) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{clientId}/cart/products")
    public ResponseEntity<?> addProductInCart(
            @PathVariable long clientId, @RequestBody Product product) throws URISyntaxException {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        cartService.addProduct(clientCart, product);

        return ResponseEntity
                .created(new URI("http://localhost:8080/clients/" + clientId + "/cart/products/" + product.getId()))
                .build();
    }

    @GetMapping("/{clientId}/cart/products")
    public ResponseEntity<?> getProductsInCart(@PathVariable long clientId) {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        List<CartProduct> clientCartProducts = cartService.getProducts(clientCart);

        return ResponseEntity.ok().body(clientCartProducts);
    }

    @PutMapping("/{clientId}/cart/products/{productId}")
    public ResponseEntity<?> changeProductCountInCart(
            @PathVariable long clientId, @PathVariable long productId, @RequestBody CartProduct cartProduct) {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        boolean isUpdated
                = cartService.changeProductCount(clientCart, productId, cartProduct.getProductCount());
        if (!isUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{clientId}/cart/products/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable long clientId, @PathVariable long productId) {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart clientCart = clientOptional.get().getCart();
        boolean isUpdated = cartService.deleteProduct(clientCart, productId);
        if (!isUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
