package com.fabeme.tp2backend.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
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

import com.fabeme.tp2backend.message.request.LoginForm;
import com.fabeme.tp2backend.message.request.SignUpForm;
import com.fabeme.tp2backend.message.response.JwtResponse;
import com.fabeme.tp2backend.model.Account;
import com.fabeme.tp2backend.model.Admin;
import com.fabeme.tp2backend.model.Role;
import com.fabeme.tp2backend.model.RoleName;
import com.fabeme.tp2backend.model.Trader;
import com.fabeme.tp2backend.repository.AccountRepository;
import com.fabeme.tp2backend.repository.AdminRepository;
import com.fabeme.tp2backend.repository.RoleRepository;
import com.fabeme.tp2backend.repository.TraderRepository;
import com.fabeme.tp2backend.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	TraderRepository traderRepository;

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
		return Optional.ofNullable(nameIndex.get("ROLE_" + name.toUpperCase()));
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
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) throws RuntimeException {
//
		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
		}

		Account user;
		// Creating user's account
		user = new Account(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getLastName(), signUpRequest.getFirstName(), signUpRequest.getPhone());

		// TRANSFERRING ACCOUNT ROLES STRING TO ENUM deez caps tho
		Set<String> requestRolesArr = Collections.singleton(signUpRequest.getRole());
		Set<Role> userRoles = new HashSet<>();

		if(requestRolesArr != null) {
			for (String roleStr : requestRolesArr) {
				Optional<RoleName> roleName = lookupRoleNameByName(roleStr);

				if(roleName.isPresent()) {
					Optional<Role> role = roleRepository.findByName(roleName.get());

					if(role.isPresent()) {
						userRoles.add(role.get());
					} else {
						return new ResponseEntity<String>("Fail -> '" + roleStr + "' is not within the role repository", HttpStatus.BAD_REQUEST);
					}

				} else {
					return new ResponseEntity<String>("Fail -> '" + roleStr + "' is not within the possible roles (RoleName enum)", HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			return new ResponseEntity<String>("Fail -> Key 'roles' is not present (null) make sure the key exist", HttpStatus.BAD_REQUEST);
		}

		user.setRoles(userRoles);

		Role traderRole = roleRepository.findByName(RoleName.ROLE_TRADER).orElseThrow(() -> {throw new RuntimeException("ROLE_TRADER NOT FOUND in  role repository");});
		Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> {throw new RuntimeException("ROLE_ADMIN NOT FOUND in  role repository");});

		if(userRoles.contains(adminRole)) {
			adminRepository.save(new Admin(user, "someLevel"));
		} else if (userRoles.contains(traderRole)) {
			traderRepository.save(new Trader(user));
		} else {
			accountRepository.save(user);
		}

		return ResponseEntity.ok().body("User registered successfully!");
	}
}