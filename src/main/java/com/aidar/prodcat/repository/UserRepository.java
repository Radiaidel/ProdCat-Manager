package com.aidar.prodcat.repository;

import com.aidar.prodcat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
