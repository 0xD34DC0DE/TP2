package com.grokonez.jwtauthentication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account", catalog = "tp2", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

	@Id
	@Column(name = "email")
	protected String email;
	protected String password;
	protected String lastName;
	protected String firstName;
	protected String adresse;
	protected String phone;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	protected Set<Role> roles = new HashSet<>();
	@Column(name = "account_status", columnDefinition = "Varchar(10) default 'ACTIF'")
	protected String status;

	public Account() {

	}

	public Account(String email, String password, String lastName, String firstName, String adresse, String phone) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adresse = adresse;
		this.phone = phone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + ", lastName=" + lastName + ", firstName="
				+ firstName + ", adresse=" + adresse + ", phone=" + phone + ", roles=" + roles + ", status=" + status
				+ "]";
	}

}
