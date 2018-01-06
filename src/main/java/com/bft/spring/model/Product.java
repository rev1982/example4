package com.bft.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="product")
public class Product extends DomainEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
