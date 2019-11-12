package com.fabeme.tp2backend.model;

import com.fabeme.tp2backend.repository.RoleRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "admins", catalog = "tp2")
@PrimaryKeyJoinColumn(referencedColumnName = "email")
public class Admin extends Account {

	//Admin level is used for what ? I don't see the need for that
	@Column(name = "admin_level", columnDefinition = "Varchar(10)")
	private String adminLevel = "someLevel";

	public Admin() {}

	public Admin(String email, String password, String lastName, String firstName, String address, String phone) {
		super(email, password, lastName, firstName, address, phone);
	}

	public Admin(Account account, String level) {
		super(account.email, account.password, account.lastName, account.firstName, account.address, account.phone);
		this.adminLevel = level;
	}

	@JsonCreator
	public Admin(
			@JsonProperty("email") String email,
			@JsonProperty("password") String password,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("address") String address,
			@JsonProperty("phone") String phone,
			@JsonProperty("roles") String[] roles) {
		super(email, password, lastName, firstName, address, phone);
	}

	public String getAdminLevel() { return adminLevel; }

	public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }

	@Override
	public String toString() {
		return "Admin{" +
				"adminLevel='" + adminLevel + '\'' +
				", roles=" + roles +
				'}';
	}
}
