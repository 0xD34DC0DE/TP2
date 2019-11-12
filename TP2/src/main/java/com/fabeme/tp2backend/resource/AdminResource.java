package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Account;
import com.fabeme.tp2backend.model.Admin;
import com.fabeme.tp2backend.repository.AccountRepository;
import com.fabeme.tp2backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin")
public class AdminResource {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/add")
	public Admin create(@RequestBody final Admin admin) {
		adminRepository.save(admin);
		return adminRepository.findByEmail(admin.getEmail());
	}



    @GetMapping("/{id}")
    public Account findById(@PathVariable final String id) {
    	return accountRepository.findById(id).orElse(new Account());
    }

	@GetMapping("/all")
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
	
	@PutMapping("/load")
    public Account update(@RequestBody final Account account) {
        accountRepository.save(account);
        return accountRepository.findById(account.getEmail()).orElse(new Account());
    }
}
