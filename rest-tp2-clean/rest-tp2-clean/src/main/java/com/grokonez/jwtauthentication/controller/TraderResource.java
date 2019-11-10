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

import com.grokonez.jwtauthentication.model.Product;
import com.grokonez.jwtauthentication.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/product")
public class TraderResource {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
	public Optional<Product> create(@RequestBody final Product product) {
		productRepository.save(product);
		return productRepository.findById(product.getProductId());
	}
    
    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable final Integer id) {
    	return productRepository.findById(id);
    }
    

	@GetMapping("/all")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
	
	@PutMapping("/load")
    public Optional<Product> update(@RequestBody final Product product) {
        productRepository.save(product);
        return productRepository.findById(product.getProductId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        productRepository.deleteById(id);
    }
}
