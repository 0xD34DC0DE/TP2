package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/traders")
public class TraderResource {

    @Autowired
    TraderRepository traderRepository;

    @PostMapping("/add")
	public Trader create(@RequestBody final Trader trader) {
        traderRepository.save(trader);
		return traderRepository.findByEmail(trader.getEmail());
	}
    
    @GetMapping("/{id}")
    public Optional<Trader> findById(@PathVariable final Integer id) {
    	return traderRepository.findById(id);
    }

	@GetMapping("/all")
    public List<Trader> findAll() {
        return traderRepository.findAll();
    }
	
	@PutMapping("/load")
    public Trader update(@RequestBody final Trader trader) {
        traderRepository.save(trader);
        return traderRepository.findByEmail(trader.getEmail());
    }
	
	@DeleteMapping("/{id}")
    public void update(@PathVariable final Integer id) {
        traderRepository.deleteById(id);
    }
}
