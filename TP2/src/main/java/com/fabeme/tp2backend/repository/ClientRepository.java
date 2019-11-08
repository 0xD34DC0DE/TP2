package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client findByEmail(String email);
	
}
