package com.bft.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="service")
public class Service extends DomainEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "default_val")
    private String defaultVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }
}

