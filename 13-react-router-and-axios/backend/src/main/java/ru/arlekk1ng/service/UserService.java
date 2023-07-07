package ru.arlekk1ng.service;

import ru.arlekk1ng.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    List<User> findAll();
    Optional<User> findById(long id);
    boolean update(User user);
}
