package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.model.Account;
import com.fabeme.tp2backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/accounts")
public class AccountResource {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/add")
    public Optional<Account> create(@RequestBody final Account account) {
        accountRepository.save(account);
        return accountRepository.findByEmail(account.getEmail());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ADMIN')")
    public Account findById(@PathVariable final String id) {
        return accountRepository.findById(id).orElse(new Account());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("@ownerService.isRecordOwner(authentication, #id) or hasRole('ADMIN')")
    public Optional<Account> update(@PathVariable String id, @RequestBody final Account account) {
        accountRepository.save(account);
        return accountRepository.findById(account.getEmail());
    }
}