package com.bft.spring.model;

/**
 * Created by rev on 06.01.2018.
 */

import javax.persistence.*;

@Entity
@Table(name = "subsidiary")
public class Subsidiary extends DomainEntity<Long> {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subsidiary_company_id", nullable = false)
    Company subsidiaryCompany;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parent_company_id", nullable = false)
    Company parentCompany;

    public Company getSubsidiaryCompany() {
        return subsidiaryCompany;
    }

    public void setSubsidiaryCompany(Company subsidiaryCompany) {
        this.subsidiaryCompany = subsidiaryCompany;
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company parentCompany) {
        this.parentCompany = parentCompany;
    }


}