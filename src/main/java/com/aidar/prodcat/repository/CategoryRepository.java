package com.aidar.prodcat.repository;

import com.aidar.prodcat.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
