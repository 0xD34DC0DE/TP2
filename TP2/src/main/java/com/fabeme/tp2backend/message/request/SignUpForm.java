package com.fabeme.tp2backend.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {
	@NotBlank
	@Size(min = 3, max = 50)
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 50)
	private String lastName;

	@NotBlank
	@Size(max = 60)
	@Email
	private String email;

	private String phone;

	private Set<String> role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private String address;

	public SignUpForm(@NotBlank @Size(min = 3, max = 50) String firstName,
					  @NotBlank @Size(min = 3, max = 50) String lastName,
					  @NotBlank @Size(max = 60) @Email String email,
					  String phone,
					  Set<String> role,
					  @NotBlank @Size(min = 6, max = 40) String password,
					  String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = password;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignUpForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", password=" + password + ", address=" + address + "]";
	}
}