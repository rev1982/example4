package com.bft.spring.model;

import javax.persistence.*;

/**
 * Created by rev on 06.01.2018.
 */
@Entity
@Table(name="sla")
public class Sla extends DomainEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contract_subject_id", nullable = false)
    private ContractSubject contractSubject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_company_id", nullable = false)
    private CustomerCompany customerCompany;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    @Column(name = "sla_sec")
    private Long slaSec;

    public ContractSubject getContractSubject() {
        return contractSubject;
    }

    public void setContractSubject(ContractSubject contractSubject) {
        this.contractSubject = contractSubject;
    }

    public CustomerCompany getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(CustomerCompany customerCompany) {
        this.customerCompany = customerCompany;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getSlaSec() {
        return slaSec;
    }

    public void setSlaSec(Long slaSec) {
        this.slaSec = slaSec;
    }
}
