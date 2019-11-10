package com.grokonez.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.model.Admin;
import com.grokonez.jwtauthentication.model.Traders;

public interface TraderRepository extends JpaRepository<Traders, Integer> {
	public Traders findByEmail(String email);
}
