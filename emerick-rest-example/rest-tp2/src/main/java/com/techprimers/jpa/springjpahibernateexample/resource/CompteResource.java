package com.techprimers.jpa.springjpahibernateexample.resource;

import java.util.List;

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

import com.techprimers.jpa.springjpahibernateexample.model.Compte;
import com.techprimers.jpa.springjpahibernateexample.repository.CompteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/compte")
public class CompteResource {

    @Autowired
    CompteRepository compteRepository;

    @PostMapping("/add")
	public Compte createCart(@RequestBody final Compte compte) {
		compteRepository.save(compte);
		return compteRepository.findOne(compte.getCompteId());
	}
    
    @GetMapping("/{id}")
    public Compte findById(@PathVariable final Integer id) {
    	return compteRepository.findOne(id);
    }
    

	@GetMapping("/all")
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }
	
	@PutMapping("/load")
    public Compte update(@RequestBody final Compte compte) {
        compteRepository.save(compte);
        return findById(compte.getCompteId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        compteRepository.delete(id);
    }
}
