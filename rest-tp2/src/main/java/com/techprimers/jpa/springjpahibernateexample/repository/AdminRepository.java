package com.techprimers.jpa.springjpahibernateexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.jpa.springjpahibernateexample.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByEmail(String email);
}
