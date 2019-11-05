package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Compte;
import com.fabeme.tp2backend.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/compte")
public class CompteResource {

    final private CompteRepository compteRepository;

    public CompteResource(@Autowired CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @PostMapping("/add")
	public Optional<Compte> createCart(@RequestBody final Compte compte) {
		compteRepository.save(compte);
		return compteRepository.findById(compte.getCompteId());
	}
    
    @GetMapping("/{id}")
    public Optional<Compte> findById(@PathVariable final Integer id) {
    	return compteRepository.findById(id);
    }
    

	@GetMapping("/all")
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }
	
	@PutMapping("/load")
    public Optional<Compte> update(@RequestBody final Compte compte) {
        compteRepository.save(compte);
        return findById(compte.getCompteId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        compteRepository.deleteById(id);
    }
}
