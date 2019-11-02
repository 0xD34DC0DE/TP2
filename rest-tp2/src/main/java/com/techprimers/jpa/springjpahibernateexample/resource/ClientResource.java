package com.techprimers.jpa.springjpahibernateexample.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.jpa.springjpahibernateexample.model.Client;
import com.techprimers.jpa.springjpahibernateexample.repository.ClientRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/client")
public class ClientResource {

    @Autowired
    ClientRepository clientRepository;

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
