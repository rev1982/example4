package com.bft.spring.model;

import javax.persistence.*;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="contract_subject")
public class ContractSubject extends DomainEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
