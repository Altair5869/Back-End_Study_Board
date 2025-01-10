package com.example.board.repository;

import com.example.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentityAndPassword(String identity, String password);

    Optional<User> findByUsername(String username);

    void deleteByIdentityAndPassword(String identity, String password);
}
