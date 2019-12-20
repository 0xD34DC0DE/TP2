package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Inventory;
import com.fabeme.tp2backend.model.Product;
import com.fabeme.tp2backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/inventories")
public class InventoryResource {

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/all")
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

}