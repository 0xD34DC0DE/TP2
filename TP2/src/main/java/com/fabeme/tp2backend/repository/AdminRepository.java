package com.fabeme.tp2backend.repository;

import com.fabeme.tp2backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByEmail(String email);
}
