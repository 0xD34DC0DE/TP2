package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.message.request.StatusForm;
import com.fabeme.tp2backend.message.request.TraderUpdateForm;
import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.model.Status;
import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.ProductRepository;
import com.fabeme.tp2backend.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder encoder;

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

            productRepository.saveAndFlush(product);

            Optional<Product> prod = productRepository.findById(product.getId());
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
               return product;
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

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id)")
    @GetMapping("/{id}/unsoldProducts")
    public Set<Product> getTraderUnsoldProduct(@PathVariable final String id) {
        Optional<Trader> trader = traderRepository.findByEmail(id);
        return trader.map(value -> value.getProducts()
                .stream()
                .filter((prod) -> {
                    return !prod.isSold();
                })
                .collect(Collectors.toSet())).orElseGet(Set::of);
    }

	@GetMapping("/all")
    public List<Trader> findAll() {
        return traderRepository.findAll();
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
    public Optional<Trader> update(@PathVariable final String id, @RequestBody final TraderUpdateForm traderForm) {

        Trader updatedTrader = new Trader(traderForm);

        Optional<Trader> originalTrader = traderRepository.findByEmail(traderForm.getEmail());
        if(originalTrader.isPresent()) {
            updatedTrader.setRoles(originalTrader.get().getRoles());

            if(traderForm.getPassword() == null) {
                updatedTrader.setPassword(originalTrader.get().getPassword());
            } else {
                updatedTrader.setPassword(encoder.encode(traderForm.getPassword()));
            }


            traderRepository.saveAndFlush(updatedTrader);

            return Optional.of(updatedTrader);
        }
        return Optional.empty();
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
        traderRepository.deleteByEmail(id);
    }

    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id)")
    @PutMapping("/{id}/products")
    public Optional<Product> updateTraderProduct(@PathVariable final String id, @RequestBody final Product product) {
       Optional<Trader> trader = traderRepository.findByEmail(id);

       if(trader.isPresent()) {
           // Check if the ID of the product is one from the list of products from this trader
           Optional<Product> productFromTrader = trader.get().getProducts().stream().filter(p -> p.getId().equals(product.getId())).findFirst();

           if(productFromTrader.isPresent()) {
               // Override the isAvailable property (this is because still don't know hoe to do a partial update and im running out of time
               product.setAvailable(productFromTrader.get().isAvailable());

               productRepository.save(product);
               return productRepository.findById(product.getId());
           }
       }
       return Optional.empty();
    }


    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/products/{prodId}")
    public void delete(@PathVariable final String id, @PathVariable final String prodId) {
        Optional<Trader> trader = traderRepository.findByEmail(id);

        if(trader.isPresent()) {
            // Check if the ID of the product is one from the list of products from this trader
            Optional<Product> productFromTrader = trader.get().getProducts().stream().filter(p -> p.getId().toString().equals(prodId)).findFirst();

            productFromTrader.ifPresent(product -> productRepository.delete(product));
        }
    }
}
