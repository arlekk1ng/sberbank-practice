package ru.arlekk1ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
