package com.fabeme.tp2backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts", catalog = "tp2", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

	@Id
	@Column(name = "email")
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	private String address;
	private String phone;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	protected Set<Role> roles = new HashSet<>();

	@Column(name = "status", columnDefinition = "varchar(10) default 'ACTIVE'")
	private String status = "ACTIVE";

	Account() {}

	public Account(String email, String password, String lastName, String firstName, String address, String phone) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
