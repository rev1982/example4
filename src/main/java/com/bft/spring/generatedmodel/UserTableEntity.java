package com.bft.spring.generatedmodel;

import javax.persistence.*;

/**
 * Created by rev on 01.01.2018.
 */
@Entity
@Table(name = "user_table", schema = "public", catalog = "test1")
public class UserTableEntity {
    private long id;
    private String userName;
    private String mobilePhone;
    private String workPhone;
    private String skype;
    private String icq;
    private String position;
    private String blockingCause;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "work_phone")
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Basic
    @Column(name = "skype")
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Basic
    @Column(name = "icq")
    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "blocking_cause")
    public String getBlockingCause() {
        return blockingCause;
    }

    public void setBlockingCause(String blockingCause) {
        this.blockingCause = blockingCause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTableEntity that = (UserTableEntity) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (skype != null ? !skype.equals(that.skype) : that.skype != null) return false;
        if (icq != null ? !icq.equals(that.icq) : that.icq != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (blockingCause != null ? !blockingCause.equals(that.blockingCause) : that.blockingCause != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (icq != null ? icq.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (blockingCause != null ? blockingCause.hashCode() : 0);
        return result;
    }
}
