package com.fabeme.tp2backend.resource;

import com.fabeme.tp2backend.message.request.LoginForm;
import com.fabeme.tp2backend.message.request.SignUpForm;
import com.fabeme.tp2backend.message.response.JwtResponse;
import com.fabeme.tp2backend.model.Account;
import com.fabeme.tp2backend.model.Role;
import com.fabeme.tp2backend.model.RoleName;
import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.AccountRepository;
import com.fabeme.tp2backend.repository.RoleRepository;
import com.fabeme.tp2backend.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

	private static final Map<String, RoleName> nameIndex = new HashMap<>(RoleName.values().length);
	// Static initializer
	static {
		for (RoleName roleName : RoleName.values()) {
			nameIndex.put(roleName.name(), roleName);
		}
	}
	// Method to lookup Role name by name returning an optional so we dont need a try catch
	public static Optional<RoleName> lookupRoleNameByName(String name) {
		return Optional.of(nameIndex.get(name));
	}

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
//
		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
		}

		Account user;
		// Creating user's account
		user = new Account(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getLastName(), signUpRequest.getFirstName(), signUpRequest.getAddress(),
				signUpRequest.getPhone());

		// TRANSFERRING ACCOUNT ROLES STRING TO ENUM deez caps tho
		Set<String> requestRolesArr = signUpRequest.getRole();
		Set<Role> userRoles = new HashSet<>();
		/*
		requestRolesArr.forEach(roleStr -> {
			lookupRoleNameByName(roleStr).ifPresentOrElse(roleName -> {
				roleRepository.findByName(roleName).ifPresent(userRoles::add);
			}, () -> {
				return new ResponseEntity<String>("Fail -> Role: " + roleStr + " is within the valid roles", HttpStatus.BAD_REQUEST);
			} );
		});
		*/
		requestRolesArr.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not found."));
				userRoles.add(adminRole);

				break;
			case "trader":
				Role traderRole = roleRepository.findByName(RoleName.ROLE_TRADER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Trader Role not found."));
				userRoles.add(traderRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Client Role not found."));
				userRoles.add(userRole);
			}
		});

		user.setRoles(userRoles);
		accountRepository.save(user);

		return ResponseEntity.ok().body("User registered successfully!");
	}
}