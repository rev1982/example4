package com.bft.spring.model;


import javax.persistence.*;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="unit")
public class Unit extends DomainEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "removedFromBalance")
    private Boolean removedFromBalance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRemovedFromBalance() {
        return removedFromBalance;
    }

    public void setRemovedFromBalance(Boolean removedFromBalance) {
        this.removedFromBalance = removedFromBalance;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
