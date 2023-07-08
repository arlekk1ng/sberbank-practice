package ru.arlekk1ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
