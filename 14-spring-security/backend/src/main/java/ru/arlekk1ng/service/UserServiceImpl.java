package ru.arlekk1ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.User;
import ru.arlekk1ng.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CartService cartService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @Override
    public User save(User user) {
        Cart savedCart = cartService.save(new Cart());
        user.setCart(savedCart);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean update(User user) {
        Optional<User> userOptional = findById(user.getId());

        if (userOptional.isEmpty()) {
            return false;
        }

        userRepository.save(user);
        return true;
    }
}
