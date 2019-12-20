package com.fabeme.tp2backend.model;

import com.fabeme.tp2backend.message.request.TraderUpdateForm;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "email")
@Table(name = "traders", catalog = "tp2")
public class Trader extends Account {

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(columnDefinition = "email", referencedColumnName = "email")
	private Set<Product> products = new TreeSet<Product>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", referencedColumnName = "id")
	private Inventory inventory = new Inventory();

	public Trader() {}

	public Trader(String email, String password, String lastName, String firstName, String phone) {
		super(email, password, lastName, firstName, phone);
	}

	public Trader(Account account) {
		super(account.email, account.password, account.lastName, account.firstName, account.phone);
		super.roles = account.roles;
	}

	public Trader(TraderUpdateForm form) {
		super(form.getEmail(), form.getPassword(), form.getLastName(), form.getFirstName(), form.getPhone());
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.inventory.setProducts(products);
		//this.products = products;
	}

	public void addProducts(Set<Product> products) {
		this.inventory.addProducts((List<Product>) products);
		this.products.addAll(products);
	}

	public void addProduct(Product product) { this.products.add(product); }

	@Override
	public String toString() {
		return "Traders [products=" + products + "]";
	}
}
