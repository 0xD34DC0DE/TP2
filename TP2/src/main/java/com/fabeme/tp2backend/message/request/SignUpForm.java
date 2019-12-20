package com.fabeme.tp2backend.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	private String role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public SignUpForm(@JsonProperty("firstName") @NotBlank @Size(min = 3, max = 50) String firstName,
					  @JsonProperty("lastName") @NotBlank @Size(min = 3, max = 50) String lastName,
					  @JsonProperty("email") @NotBlank @Size(max = 60) @Email String email,
					  @JsonProperty("phone") String phone,
					  @JsonProperty("role") String role,
					  @JsonProperty("password") @NotBlank @Size(min = 6, max = 40) String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = password;
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

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignUpForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", password=" + password + "]";
	}
}