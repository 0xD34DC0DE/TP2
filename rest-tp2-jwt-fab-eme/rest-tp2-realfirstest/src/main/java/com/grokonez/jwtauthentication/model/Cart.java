package com.grokonez.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "relationClass" })
@Table(name = "cart", catalog = "tp2")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private Integer cartId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Set<CartItem> cartItemList;

	@Column(name = "amount", columnDefinition = "Decimal(10,2) default '0.00'")
	private double amount;

	@Column(name = "cart_status", columnDefinition = "Varchar(10) default 'ACTIF'")
	private String status;

	public Cart() {
	}

	public String getStatus() {
		return status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getCompteId() {
		return cartId;
	}

	public void setCompteId(Integer compteId) {
		this.cartId = compteId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Set<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(Set<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartItemList=" + cartItemList + ", amount=" + amount + ", status=" + status
				+ "]";
	}

}
