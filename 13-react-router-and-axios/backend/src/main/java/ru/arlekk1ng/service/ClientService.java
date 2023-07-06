package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(long id);
    boolean update(Client client);
}
