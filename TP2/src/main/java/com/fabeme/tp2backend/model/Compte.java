package com.fabeme.tp2backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "relationClass" })
@Table(name = "compte", catalog = "tp2")
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "compte_id")
	private Integer compteId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "compte_id")
	private Set<Product> productList;

	@Column(name = "amount", columnDefinition = "Decimal(10,2) default '0.00'")
	private double amount;

	@Column(name = "cart_status", columnDefinition = "Varchar(10) default 'ACTIF'")
	private String status;

	public Compte() {
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
		return compteId;
	}

	public void setCompteId(Integer compteId) {
		this.compteId = compteId;
	}

	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Compte [compteId=" + compteId + ", productList=" + productList + ", amount=" + amount + ", status="
				+ status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((compteId == null) ? 0 : compteId.hashCode());
		result = prime * result + ((productList == null) ? 0 : productList.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (compteId == null) {
			if (other.compteId != null)
				return false;
		} else if (!compteId.equals(other.compteId))
			return false;
		if (productList == null) {
			if (other.productList != null)
				return false;
		} else if (!productList.equals(other.productList))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
