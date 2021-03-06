package com.bft.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="priority")
public class Priority extends DomainEntity<Long> {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
