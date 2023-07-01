package ru.arlekk1ng.service.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Client;
import ru.arlekk1ng.repository.CartRepository;
import ru.arlekk1ng.service.response.entity.CartResponse;
import ru.arlekk1ng.service.response.entity.ClientResponse;

@Service
public class ClientResponseService {
    private CartRepository cartRepository;
    private CartResponseService cartResponseService;

    @Autowired
    public ClientResponseService(
            CartRepository cartRepository,
            CartResponseService cartResponseService
    ) {
        this.cartRepository = cartRepository;
        this.cartResponseService = cartResponseService;
    }

    public ClientResponse getClientResponseFromClient(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setName(client.getName());
        CartResponse cart = cartResponseService.getCartResponseFromCart(
                cartRepository.findById(client.getCartId()).get()
        );
        clientResponse.setCart(cart);
        return clientResponse;
    }
}
