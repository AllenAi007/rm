package com.ai.sin.rm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Product POJO
 */
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer risk;
    @Column
    private Integer investment;

    public Product() {
    }

    public Product(String name, Integer risk, Integer investment) {
        this.setName(name);
        this.setRisk(risk);
        this.setInvestment(investment);
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

    public Integer getInvestment() {
        return investment;
    }

    public void setInvestment(Integer investment) {
        this.investment = investment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(risk, product.risk) &&
                Objects.equals(investment, product.investment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, risk, investment);
    }
}
