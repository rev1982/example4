package com.bft.spring.generatedmodel;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by rev on 19.12.2017.
 */
@Entity
@Table(name = "company", schema = "public", catalog = "test1")
public class CompanyEntity {
    //private static final int INCREMENT_COMPANY_SIZE_VALUE = 7;
    private long id;
    private String shortName;
    private String fullName;
    private String legalAdress;
    private String actualAdress;
    private String inn;
    private String kpp;
    private String fias;
    private String phone;
    private Boolean actual;
    private String vip;
    private Long timeZoneId;
    private Time workFrom;
    private Time workUntil;
    private Time lunchfrom;
    private Time lunchUntil;
    private Long subdivisionDeId;
    private Long puId;
    private String note;

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence")
//    @SequenceGenerator(name = "company_sequence",
//      sequenceName = "company_sequence",
//      allocationSize = INCREMENT_COMPANY_SIZE_VALUE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "legal_adress")
    public String getLegalAdress() {
        return legalAdress;
    }

    public void setLegalAdress(String legalAdress) {
        this.legalAdress = legalAdress;
    }

    @Basic
    @Column(name = "actual_adress")
    public String getActualAdress() {
        return actualAdress;
    }

    public void setActualAdress(String actualAdress) {
        this.actualAdress = actualAdress;
    }

    @Basic
    @Column(name = "inn")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Basic
    @Column(name = "kpp")
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @Basic
    @Column(name = "fias")
    public String getFias() {
        return fias;
    }

    public void setFias(String fias) {
        this.fias = fias;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    @Basic
    @Column(name = "vip")
    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    @Basic
    @Column(name = "time_zone_id")
    public Long getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(Long timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Basic
    @Column(name = "work_from")
    public Time getWorkFrom() {
        return workFrom;
    }

    public void setWorkFrom(Time workFrom) {
        this.workFrom = workFrom;
    }

    @Basic
    @Column(name = "work_until")
    public Time getWorkUntil() {
        return workUntil;
    }

    public void setWorkUntil(Time workUntil) {
        this.workUntil = workUntil;
    }

    @Basic
    @Column(name = "lunchfrom")
    public Time getLunchfrom() {
        return lunchfrom;
    }

    public void setLunchfrom(Time lunchfrom) {
        this.lunchfrom = lunchfrom;
    }

    @Basic
    @Column(name = "lunch_until")
    public Time getLunchUntil() {
        return lunchUntil;
    }

    public void setLunchUntil(Time lunchUntil) {
        this.lunchUntil = lunchUntil;
    }

    @Basic
    @Column(name = "subdivision_de_id")
    public Long getSubdivisionDeId() {
        return subdivisionDeId;
    }

    public void setSubdivisionDeId(Long subdivisionDeId) {
        this.subdivisionDeId = subdivisionDeId;
    }

    @Basic
    @Column(name = "pu_id")
    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (id != that.id) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (legalAdress != null ? !legalAdress.equals(that.legalAdress) : that.legalAdress != null) return false;
        if (actualAdress != null ? !actualAdress.equals(that.actualAdress) : that.actualAdress != null) return false;
        if (inn != null ? !inn.equals(that.inn) : that.inn != null) return false;
        if (kpp != null ? !kpp.equals(that.kpp) : that.kpp != null) return false;
        if (fias != null ? !fias.equals(that.fias) : that.fias != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (vip != null ? !vip.equals(that.vip) : that.vip != null) return false;
        if (timeZoneId != null ? !timeZoneId.equals(that.timeZoneId) : that.timeZoneId != null) return false;
        if (workFrom != null ? !workFrom.equals(that.workFrom) : that.workFrom != null) return false;
        if (workUntil != null ? !workUntil.equals(that.workUntil) : that.workUntil != null) return false;
        if (lunchfrom != null ? !lunchfrom.equals(that.lunchfrom) : that.lunchfrom != null) return false;
        if (lunchUntil != null ? !lunchUntil.equals(that.lunchUntil) : that.lunchUntil != null) return false;
        if (subdivisionDeId != null ? !subdivisionDeId.equals(that.subdivisionDeId) : that.subdivisionDeId != null)
            return false;
        if (puId != null ? !puId.equals(that.puId) : that.puId != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (legalAdress != null ? legalAdress.hashCode() : 0);
        result = 31 * result + (actualAdress != null ? actualAdress.hashCode() : 0);
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (kpp != null ? kpp.hashCode() : 0);
        result = 31 * result + (fias != null ? fias.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (vip != null ? vip.hashCode() : 0);
        result = 31 * result + (timeZoneId != null ? timeZoneId.hashCode() : 0);
        result = 31 * result + (workFrom != null ? workFrom.hashCode() : 0);
        result = 31 * result + (workUntil != null ? workUntil.hashCode() : 0);
        result = 31 * result + (lunchfrom != null ? lunchfrom.hashCode() : 0);
        result = 31 * result + (lunchUntil != null ? lunchUntil.hashCode() : 0);
        result = 31 * result + (subdivisionDeId != null ? subdivisionDeId.hashCode() : 0);
        result = 31 * result + (puId != null ? puId.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
