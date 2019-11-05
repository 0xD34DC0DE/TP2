package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Client;
import com.fabeme.tp2backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/client")
public class ClientResource {

    private final ClientRepository clientRepository;

    public ClientResource(@Autowired ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/all")
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
    
    @PostMapping("/add")
    public Client createClient(@RequestBody final Client client) {
    	clientRepository.save(client);
    	return clientRepository.findByEmail(client.getEmail());
    }
}
