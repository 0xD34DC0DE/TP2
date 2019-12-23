package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.message.request.IdForm;
import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/products")
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
	public Optional<Product> create(@RequestBody final Product product) {
		productRepository.save(product);
		return productRepository.findById(product.getId());
	}

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable final Integer id) {
    	return productRepository.findById(id);
    }


	@GetMapping("/all")
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @GetMapping("/allAvailable")
    public Product[] findAllAvailable() {
        return productRepository.findAllByAvailableTrueAndHiddenFalse();
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

    @PostMapping("/sell")
    public Optional<Product> sellProduct(@RequestBody final IdForm idForm) {
        Optional<Product> product = productRepository.findById(idForm.getId());
        if(product.isPresent()) {
            Product prod = product.get();
            prod.setSold(true);
            productRepository.saveAndFlush(prod);
            return Optional.of(prod);
        }

        return Optional.empty();
    }

    @PostMapping("/return")
    public Optional<Product> returnProduct(@RequestBody final IdForm idForm) {
        Optional<Product> product = productRepository.findById(idForm.getId());
        if(product.isPresent()) {
            Product prod = product.get();
            prod.setSold(false);
            productRepository.saveAndFlush(prod);
            return Optional.of(prod);
        }

        return Optional.empty();
    }
}
