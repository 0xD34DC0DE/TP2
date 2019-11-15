package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
public Optional<Product> findByName(String name);
}
