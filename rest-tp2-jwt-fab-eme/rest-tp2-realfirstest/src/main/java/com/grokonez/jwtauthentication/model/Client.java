package com.grokonez.jwtauthentication.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "email")
@Table(name = "client", catalog = "tp2")
public class Client extends Account {
	@OneToMany
	@JoinColumn(columnDefinition = "email", referencedColumnName = "email")
	private Set<Cart> cart = new TreeSet<Cart>();

	@Column(name = "balance", columnDefinition = "Decimal(10,2) default '0.00'")
	private double balance;

	public Client() {
	}

	public Client(String email, String password, String lastName, String firstName, String adresse, String phone) {
		super(email, password, lastName, firstName, adresse, phone);
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Client [cart=" + cart + ", balance=" + balance + "]";
	}

}
