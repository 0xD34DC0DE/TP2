package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
