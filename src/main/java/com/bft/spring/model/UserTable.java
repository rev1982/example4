package com.bft.spring.model;

import javax.persistence.*;

/**
 * Created by rev on 01.01.2018.
 */
@Entity
@Table(name = "user_table")
public class UserTable extends DomainEntity<Long> {
    @Column(name = "user_name")
    private String userName;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "work_phone")
    private String workPhone;
    @Column(name = "skype")
    private String skype;
    @Column(name = "icq")
    private String icq;
    @Column(name = "position")
    private String position;
    @Column(name = "blocking_cause")
    private String blockingCause;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBlockingCause() {
        return blockingCause;
    }

    public void setBlockingCause(String blockingCause) {
        this.blockingCause = blockingCause;
    }

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

}
