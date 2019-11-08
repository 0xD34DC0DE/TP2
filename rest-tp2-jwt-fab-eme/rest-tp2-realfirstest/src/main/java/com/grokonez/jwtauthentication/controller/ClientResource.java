package com.grokonez.jwtauthentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.model.Client;
import com.grokonez.jwtauthentication.repository.ClientRepository;

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
    public Optional<Client> createClient(@RequestBody final Client client) {
    	clientRepository.save(client);
    	return clientRepository.findByEmail(client.getEmail());
    }
}
