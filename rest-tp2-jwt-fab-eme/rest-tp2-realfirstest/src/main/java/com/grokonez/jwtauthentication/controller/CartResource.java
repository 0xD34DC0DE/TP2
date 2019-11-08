package com.grokonez.jwtauthentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.model.Cart;
import com.grokonez.jwtauthentication.repository.CartRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/compte")
public class CartResource {

    @Autowired
    CartRepository compteRepository;

    @PostMapping("/add")
	public Optional<Cart> create(@RequestBody final Cart compte) {
		compteRepository.save(compte);
		return compteRepository.findById(compte.getCompteId());
	}
    
    @GetMapping("/{id}")
    public Optional<Cart> findById(@PathVariable final Integer id) {
    	return compteRepository.findById(id);
    }
    

	@GetMapping("/all")
    public List<Cart> findAll() {
        return compteRepository.findAll();
    }
	
	@PutMapping("/load")
    public Optional<Cart> update(@RequestBody final Cart compte) {
        compteRepository.save(compte);
        return findById(compte.getCompteId());
    }
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        compteRepository.deleteById(id);
    }
}
