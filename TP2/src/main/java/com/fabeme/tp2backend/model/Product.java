package com.fabeme.tp2backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "relationClass" })
@Table(name = "products", catalog = "tp2")
public class Product {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	private double price;
	@Column(name = "available_stock")
	private String description = "";

	private boolean available = true;

	private boolean hidden = false;

	private boolean sold = false;

	public Product() {
	}

	public Product(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.available = product.isAvailable();
		this.hidden = product.isHidden();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isHidden() { return hidden; }

	public void setHidden(boolean hidden) { this.hidden = hidden; }

	public boolean isSold() { return sold; }

	public void setSold(boolean sold) { this.sold = sold; }

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, description, available, hidden);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description != other.description)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + id + ", productName=" + name + ", price=" + price
				+ ", availableStock=" + description + "]";
	}

}
