package com.grokonez.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	Optional<Account> findByEmail(String email);

	Boolean existsByEmail(String email);
}
