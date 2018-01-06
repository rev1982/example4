package com.bft.spring.model;

/**
 * Created by rev on 06.01.2018.
 */

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contract")
public class Contract extends DomainEntity<Long> {
    @Column(name = "contract_date")
    private Date contractDate;
    @Column(name = "number")
    private String number;
    @Column(name = "pu_number")
    private String puNumber;
    @Column(name = "status")
    private String status;
    @Column(name = "pu_project")
    private String puProject;
    @Column(name = "valid_from")
    private Date validFrom;
    @Column(name = "valid_until")
    private Date validUntil;
    @Column(name = "is_valid")
    private Boolean isValid;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_company_id", nullable = false)
    private Company customerCompany;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subdivision_pu_id", nullable = false)
    private SubdivisionPU subdivisionPU;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractType contractType;

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPuNumber() {
        return puNumber;
    }

    public void setPuNumber(String puNumber) {
        this.puNumber = puNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPuProject() {
        return puProject;
    }

    public void setPuProject(String puProject) {
        this.puProject = puProject;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public void setCustomerCompany(Company customerCompany) {
        this.customerCompany = customerCompany;
    }

    public void setSubdivisionPU(SubdivisionPU subdivisionPU) {
        this.subdivisionPU = subdivisionPU;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Company getCustomerCompany() {
        return customerCompany;
    }

    public SubdivisionPU getSubdivisionPU() {
        return subdivisionPU;
    }

    public ContractType getContractType() {
        return contractType;
    }

}