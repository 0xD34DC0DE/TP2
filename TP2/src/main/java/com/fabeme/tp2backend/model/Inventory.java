package com.fabeme.tp2backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inventory {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    private List<Product> products;

    public Inventory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) { this.products.add(product); }

    public void addProducts(List<Product> products) { this.products.addAll(products); }
}
