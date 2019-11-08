package com.fabeme.tp2backend.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "email")
@Table(name = "clients", catalog = "tp2")
public class Client extends Account {

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
	private Cart cart;
	
	@Column(name = "balance", columnDefinition = "Decimal(10,2) default '0.00'")
	private double balance = 0.0;

	public Client() {
		type = AccountTypes.CLIENT;
		cart = new Cart();
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
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
		return "Client [compte=" + cart + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		return true;
	}

}
