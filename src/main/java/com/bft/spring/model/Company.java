package com.bft.spring.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by rev on 19.12.2017.
 */
@Entity
@Table(name="COMPANY")
public class Company extends DomainEntity<Long> {
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "legal_adress")
    private String legalAdress;
    @Column(name = "actual_adress")
    private String actualAdress;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "fias")
    private String fias;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "actual")
    private Boolean actual;
    @Column(name = "vip")
    private String vip;
@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinColumn(name = "time_zone_id", nullable = false)
    private TimeZone timeZone;
    @Column(name = "work_from")
    private Time workFrom;
    @Column(name = "work_until")
    private Time workUntil;
    @Column(name = "lunch_from")
    private Time lunchFrom;
    @Column(name = "lunch_until")
    private Time lunchUntil;
    @Column(name = "subdivision_De_Id")
    private Long subdivisionDeId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pu_id", nullable = false)
    private SubdivisionPU subdivisionPU;
    @Column(name = "note")
    private String note;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLegalAdress() {
        return legalAdress;
    }

    public void setLegalAdress(String legalAdress) {
        this.legalAdress = legalAdress;
    }

    public String getActualAdress() {
        return actualAdress;
    }

    public void setActualAdress(String actualAdress) {
        this.actualAdress = actualAdress;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @Column(name = "fias")
    public String getFias() {
        return fias;
    }

    public void setFias(String fias) {
        this.fias = fias;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Time getWorkFrom() {
        return workFrom;
    }

    public void setWorkFrom(Time workFrom) {
        this.workFrom = workFrom;
    }

    public Time getWorkUntil() {
        return workUntil;
    }

    public void setWorkUntil(Time workUntil) {
        this.workUntil = workUntil;
    }

    public Time getLunchFrom() {
        return lunchFrom;
    }

    public void setLunchFrom(Time lunchFrom) {
        this.lunchFrom = lunchFrom;
    }

    public Time getLunchUntil() {
        return lunchUntil;
    }

    public void setLunchUntil(Time lunchUntil) {
        this.lunchUntil = lunchUntil;
    }

    public Long getSubdivisionDeId() {
        return subdivisionDeId;
    }

    public void setSubdivisionDeId(Long subdivisionDeId) {
        this.subdivisionDeId = subdivisionDeId;
    }

    public SubdivisionPU getSubdivisionPU() {
        return subdivisionPU;
    }

    public void setSubdivisionPU(SubdivisionPU subdivisionPU) {
        this.subdivisionPU = subdivisionPU;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
