package com.grokonez.jwtauthentication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.model.Account;
import com.grokonez.jwtauthentication.repository.AccountRepository;

@RestController
public class TestRestAPIs {
	
	@Autowired
	AccountRepository userRepository;
	
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('TRADER') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Board Management Project";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	@GetMapping("/api/test/users/{email}")
	@PreAuthorize("hasRole('CLIENT')")
	public Optional<Account> usersAccess(@PathVariable final String email) {
		return userRepository.findByEmail(email);
	}
}