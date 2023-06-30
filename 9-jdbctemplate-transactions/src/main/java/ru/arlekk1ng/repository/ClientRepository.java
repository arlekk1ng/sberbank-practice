package ru.arlekk1ng.repository;

import ru.arlekk1ng.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    long save(Client client);
    Optional<Client> findById(long clientId);
    List<Client> findAll();
    boolean deleteById(long clientId);
}
