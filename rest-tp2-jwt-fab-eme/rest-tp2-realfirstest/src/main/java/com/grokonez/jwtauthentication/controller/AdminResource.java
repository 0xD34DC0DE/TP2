package com.grokonez.jwtauthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.model.Admin;
import com.grokonez.jwtauthentication.repository.AdminRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/admin")
public class AdminResource {

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/add")
	public Admin create(@RequestBody final Admin admin) {
		adminRepository.save(admin);
		return adminRepository.findByEmail(admin.getEmail());
	}
    
    @GetMapping("/{id}")
    public Admin findById(@PathVariable final String id) {
    	return adminRepository.findByEmail(id);
    }
    

	@GetMapping("/all")
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
	
	@PutMapping("/load")
    public Admin update(@RequestBody final Admin admin) {
        adminRepository.save(admin);
        return findById(admin.getEmail());
    }
}
