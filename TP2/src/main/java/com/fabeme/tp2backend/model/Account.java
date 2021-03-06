package com.fabeme.tp2backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts", catalog = "tp2")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

	@Id
	@Column(name = "email")
	protected String email;
	protected String password;
	protected String lastName;
	protected String firstName;
	protected String phone;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	protected Set<Role> roles = new HashSet<>();

	@Column(name = "status", columnDefinition = "varchar(10) default 'ACTIVE'")
	protected String status = "ACTIVE";

	public Account() {}

	public Account(String email, String password, String lastName, String firstName, String phone) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
