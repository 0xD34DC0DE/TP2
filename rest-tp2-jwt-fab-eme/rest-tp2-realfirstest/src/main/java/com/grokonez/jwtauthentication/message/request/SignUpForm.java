package com.grokonez.jwtauthentication.message.request;

import java.util.Set;

import javax.validation.constraints.*;

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

	private String adresse;

	private SignType signInType;

	public String getFirstName() {
		return firstName;
	}

	public SignType getSignInType() {
		return signInType;
	}

	public void setSignInType(SignType signInType) {
		this.signInType = signInType;
	}

	@Override
	public String toString() {
		return "SignUpForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", password=" + password + ", adresse=" + adresse + "]";
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
}