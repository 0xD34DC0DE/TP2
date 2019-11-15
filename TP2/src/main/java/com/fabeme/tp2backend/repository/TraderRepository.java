package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraderRepository extends JpaRepository<Trader, Integer> {
	public Optional<Trader> findByEmail(String email);
}
