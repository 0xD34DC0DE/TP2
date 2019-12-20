package com.fabeme.tp2backend.repository;


import com.fabeme.tp2backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}