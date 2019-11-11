package com.fabeme.tp2backend.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fabeme.tp2backend.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String lastName;
	private String firstName;
	private String address;
	private String phone;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(String lastName, String firstName, String address, String phone, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple build(Account user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserPrinciple(user.getLastName(), user.getFirstName(), user.getAddress(), user.getPhone(),
				user.getEmail(), user.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public String toString() {
		return "UserPrinciple [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", password=" + password + ", authorities="
				+ authorities + "]";
	}

	@Override
	public String getUsername() {
		return email;
	}
}