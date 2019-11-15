package com.fabeme.tp2backend.model;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "email")
@Table(name = "traders", catalog = "tp2")
public class Trader extends Account {

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(columnDefinition = "email", referencedColumnName = "email")
	private Set<Product> products = new TreeSet<Product>();

	public Trader() {}

	public Trader(String email, String password, String lastName, String firstName, String address, String phone) {
		super(email, password, lastName, firstName, address, phone);
	}

	public Trader(Account account) {
		super(account.email, account.password, account.lastName, account.firstName, account.address, account.phone);
		super.roles = account.roles;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public void addProducts(Set<Product> products) { this.products.addAll(products); }

	public void addProduct(Product product) { this.products.add(product); }

	@Override
	public String toString() {
		return "Traders [products=" + products + "]";
	}
}
