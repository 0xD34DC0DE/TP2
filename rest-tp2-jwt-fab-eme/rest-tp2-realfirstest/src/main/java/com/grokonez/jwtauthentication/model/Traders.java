package com.grokonez.jwtauthentication.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "email")
@Table(name = "traders", catalog = "tp2")
public class Traders extends Account {
	@OneToMany
	@JoinColumn(columnDefinition = "email", referencedColumnName = "email")
	private Set<Product> trader = new TreeSet<Product>();
	private double balance;

	public Traders() {
	}

	public Traders(String email, String encode, String lastName, String firstName, String adresse, String phone) {
		super(email, encode, lastName, firstName, adresse, phone);
	}

	public Set<Product> getCompte() {
		return trader;
	}

	public void setCompte(Set<Product> productList) {
		this.trader = productList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Traders [product=" + trader + ", balance=" + balance + "]";
	}

}
