package com.fabeme.tp2backend.security.services;

import com.fabeme.tp2backend.model.Account;
import com.fabeme.tp2backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        Account user = accountRepository.findByEmail(username)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );

        return UserPrinciple.build(user);
    }
}