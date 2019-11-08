package com.grokonez.jwtauthentication.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.message.request.LoginForm;
import com.grokonez.jwtauthentication.message.request.SignType;
import com.grokonez.jwtauthentication.message.request.SignUpForm;
import com.grokonez.jwtauthentication.message.response.JwtResponse;
import com.grokonez.jwtauthentication.model.Account;
import com.grokonez.jwtauthentication.model.Client;
import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import com.grokonez.jwtauthentication.model.Traders;
import com.grokonez.jwtauthentication.repository.AccountRepository;
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
//        if(accountRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity<String>("Fail -> Username is already taken!",
//                    HttpStatus.BAD_REQUEST);
//        }

		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
		}
		Account user;
		// Creating user's account
		if (signUpRequest.getSignInType() == SignType.CLIENT) {
			user = new Client(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
					signUpRequest.getLastName(), signUpRequest.getFirstName(), signUpRequest.getAdresse(),
					signUpRequest.getPhone());

		} else {
			user = new Traders(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
					signUpRequest.getLastName(), signUpRequest.getFirstName(), signUpRequest.getAdresse(),
					signUpRequest.getPhone());
		}

		// TRANSFERING ACCOUNT ROLES STRING TO ENUM
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));
				roles.add(adminRole);

				break;
			case "trader":
				Role pmRole = roleRepository.findByName(RoleName.ROLE_TRADER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Trader Role not find."));
				roles.add(pmRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Client Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		accountRepository.save(user);

		return ResponseEntity.ok().body("User registered successfully!");
	}
}