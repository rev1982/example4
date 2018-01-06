package com.bft.spring.model;

import javax.persistence.*;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="sold_unit")
public class SoldUnit extends DomainEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contract_subject_id", nullable = false)
    private ContractSubject contractSubject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    public ContractSubject getContractSubject() {
        return contractSubject;
    }

    public void setContractSubject(ContractSubject contractSubject) {
        this.contractSubject = contractSubject;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
