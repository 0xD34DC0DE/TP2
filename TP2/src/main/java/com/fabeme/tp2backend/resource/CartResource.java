package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Cart;
import com.fabeme.tp2backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/cart")
public class CartResource {

    final private CartRepository cartRepository;

    public CartResource(@Autowired CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping("/add")
	public Optional<Cart> createCart(@RequestBody final Cart cart) {
		cartRepository.save(cart);
		return cartRepository.findById(cart.getCartId());
	}
    
    @GetMapping("/{id}")
    public Optional<Cart> findById(@PathVariable final Integer id) {
    	return cartRepository.findById(id);
    }
    

	@GetMapping("/all")
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }
	
	@PutMapping("/load")
    public Optional<Cart> update(@RequestBody final Cart cart) {
        cartRepository.save(cart);
        return findById(cart.getCartId());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        cartRepository.deleteById(id);
    }
}
