package com.fabeme.tp2backend.resource;


import com.fabeme.tp2backend.model.Cart;
import com.fabeme.tp2backend.model.Client;
import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.repository.ClientRepository;
import com.fabeme.tp2backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/client")
public class ClientResource {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ClientResource(@Autowired ClientRepository clientRepository, @Autowired ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable final String id) {
        return clientRepository.findByEmail(id);
    }

    @GetMapping("/{id}/cart")
    public Cart getClientCart(@PathVariable final String id) {
        return clientRepository.findByEmail(id).getCart();
    }

    @PostMapping("/{id}/cart/productList")
    public List<Product> addClientCartProduct(@PathVariable final String id, @RequestBody final Map<String, Integer> productId) {
        productRepository.findById(productId.get("id")).ifPresent(value -> clientRepository.findByEmail(id).getCart().addProduct(value));
        return clientRepository.findByEmail(id).getCart().getProductList();
    }

    @PostMapping("/add")
    public Client createClient(@RequestBody final Client client) {
    	clientRepository.save(client);
    	return clientRepository.findByEmail(client.getEmail());
    }
}
