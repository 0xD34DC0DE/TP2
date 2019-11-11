package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, Integer> {
	public Trader findByEmail(String email);
}
