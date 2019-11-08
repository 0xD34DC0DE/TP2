package com.grokonez.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByEmail(String email);
	
}
