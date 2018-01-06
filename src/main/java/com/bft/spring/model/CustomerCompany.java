package com.bft.spring.model;

/**
 * Created by rev on 06.01.2018.
 */

import javax.persistence.*;

@Entity
@Table(name = "customer_company")
public class CustomerCompany extends DomainEntity<Long> {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contract_id", nullable = false)
    Contract contract;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subsidiary_id", nullable = false)
    Subsidiary subsidiary;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}