package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Admin;
import com.fabeme.tp2backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
