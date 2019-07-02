package com.ai.sin.rm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Client POJO
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer risk;
    @JsonProperty("recommendations")
    private transient Set<Product> products;// recommendation products

    public Client() {
        this.products = new HashSet<>();
    }

    public Client(Integer id, String name, Integer risk) {
        this();
        this.setName(name);
        this.setRisk(risk);
        this.setId(id);
    }

    public Client(String name, Integer risk) {
        this();
        this.setName(name);
        this.setRisk(risk);
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

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(risk, client.risk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, risk);
    }
}
