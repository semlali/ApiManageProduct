package com.example.producttrialmaster.repository;

import com.example.producttrialmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByUsername, findByEmail, etc.

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

}
