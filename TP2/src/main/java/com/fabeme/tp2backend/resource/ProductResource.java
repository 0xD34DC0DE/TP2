package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/product")
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
	public Optional<Product> create(@RequestBody final Product product) {
		productRepository.save(product);
		return productRepository.findById(product.getId());
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
        return productRepository.findById(product.getId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        productRepository.deleteById(id);
    }
}
