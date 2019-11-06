package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
