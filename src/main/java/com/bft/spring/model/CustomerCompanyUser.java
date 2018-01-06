package com.bft.spring.model;

/**
 * Created by rev on 06.01.2018.
 */

import javax.persistence.*;

@Entity
@Table(name = "customer_company_user")
public class CustomerCompanyUser extends DomainEntity<Long> {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_company_id", nullable = false)
    CustomerCompany customerCompany;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    UserTable user;

    public CustomerCompany getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(CustomerCompany customerCompany) {
        this.customerCompany = customerCompany;
    }

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }
}