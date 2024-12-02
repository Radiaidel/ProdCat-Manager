package com.aidar.prodcat.repository;

import com.aidar.prodcat.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
