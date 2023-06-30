package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalClientRepository implements ClientRepository {
    private List<Client> clients = new ArrayList<>();
    private ProductCartRepository productCartRepository;

    public LocalClientRepository(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    @Override
    public long save(Client client) {
        productCartRepository.save(client.getCart());

        clients.add(client);
        return client.getId();
    }

    @Override
    public Optional<Client> findById(long clientId) {
        return clients.stream()
                .filter(client -> client.getId() == clientId)
                .findAny();
    }

    @Override
    public boolean deleteById(long clientId) {
        return clients.removeIf(client -> client.getId() == clientId);
    }
}
