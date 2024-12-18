package com.aidar.prodcat.repository;

import com.aidar.prodcat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);
    boolean existsByUsername(String username);

}