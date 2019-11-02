package com.techprimers.jpa.springjpahibernateexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.jpa.springjpahibernateexample.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client findByEmail(String email);
	
}
