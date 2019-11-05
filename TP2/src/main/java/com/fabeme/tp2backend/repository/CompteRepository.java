package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
}
