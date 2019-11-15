package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.ProductRepository;
import com.fabeme.tp2backend.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest/traders")
public class TraderResource {

    @Autowired
    TraderRepository traderRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
	public Optional<Trader> create(@RequestBody final Trader trader) {
        traderRepository.save(trader);
		return traderRepository.findByEmail(trader.getEmail());
	}
    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('AMDIN')")
    @GetMapping("/{id}")
    public Optional<Trader> findByEmail(@PathVariable final String id) {
    	return traderRepository.findByEmail(id);
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id)")
    @PostMapping("/{id}/products")
    public Optional<Product> getTraderProduct(@PathVariable final String id, @RequestBody final Product product) {
        Optional<Trader> trader = traderRepository.findByEmail(id);
        if(trader.isPresent()) {
            Trader currentTrader = trader.get();

            productRepository.save(product);

            Optional<Product> prod = productRepository.findByName(product.getName());
            if(prod.isPresent()){
                currentTrader.addProduct(prod.get());
                traderRepository.save(currentTrader);
                return prod;
            };
        }
        return Optional.empty();
    }

    @GetMapping("/{id}/products")
    public Set<Product> getTraderProduct(@PathVariable final Integer id) {
        Optional<Trader> trader = traderRepository.findById(id);
        return trader.map(Trader::getProducts).orElse(null);
    }

	@GetMapping("/all")
    public List<Trader> findAll() {
        return traderRepository.findAll();
    }
	
	@PutMapping("/load")
    public Optional<Trader> update(@RequestBody final Trader trader) {
        traderRepository.save(trader);
        return traderRepository.findByEmail(trader.getEmail());
    }
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        traderRepository.deleteById(id);
    }
}
