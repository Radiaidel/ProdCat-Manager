package com.aidar.prodcat.repository;

import com.aidar.prodcat.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
