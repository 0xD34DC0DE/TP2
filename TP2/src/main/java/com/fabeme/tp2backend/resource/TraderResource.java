package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.message.request.StatusForm;
import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.model.Status;
import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.ProductRepository;
import com.fabeme.tp2backend.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Transactional
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public Boolean setTraderStatus(@PathVariable final String id, @RequestBody final StatusForm statusForm) {
       Optional<Trader> trader = traderRepository.findByEmail(id);
       trader.ifPresent((tra -> {
           tra.setStatus(statusForm.getStatus().toString());
           traderRepository.save(tra);

           boolean active = statusForm.isStatusActive();

           Set<Product> products = tra.getProducts();
           Set<Product> updatedProducts = products.stream().map(product -> {
               product.setAvailable(active);
               productRepository.save(product);
               return productRepository.findById(product.getId()).get();
           }).collect(Collectors.toSet());;

           productRepository.saveAll(updatedProducts);
       }));

        return trader.isPresent();
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id)")
    @GetMapping("/{id}/products")
    public Set<Product> getTraderProduct(@PathVariable final String id) {
        Optional<Trader> trader = traderRepository.findByEmail(id);
        return trader.map(Trader::getProducts).orElse(null);
    }

	@GetMapping("/all")
    public List<Trader> findAll() {
        return traderRepository.findAll();
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
    public Optional<Trader> update(@PathVariable final String id, @RequestBody final Trader trader) {
        traderRepository.save(trader);
        return traderRepository.findByEmail(trader.getEmail());
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
        traderRepository.deleteByEmail(id);
    }
}
