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

import com.techprimers.jpa.springjpahibernateexample.model.Product;
import com.techprimers.jpa.springjpahibernateexample.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/product")
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
	public Product create(@RequestBody final Product product) {
		productRepository.save(product);
		return productRepository.findOne(product.getProductId());
	}
    
    @GetMapping("/{id}")
    public Product findById(@PathVariable final Integer id) {
    	return productRepository.findOne(id);
    }
    

	@GetMapping("/all")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
	
	@PutMapping("/load")
    public Product update(@RequestBody final Product product) {
        productRepository.save(product);
        return productRepository.findOne(product.getProductId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        productRepository.delete(id);
    }
}
