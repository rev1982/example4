package com.bft.spring.model;

/**
 * Created by rev on 06.01.2018.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contract_type")
public class ContractType extends DomainEntity<Long> {
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
