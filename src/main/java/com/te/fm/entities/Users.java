/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName")
    , @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName")
    , @NamedQuery(name = "Users.findByEmailAddress", query = "SELECT u FROM Users u WHERE u.emailAddress = :emailAddress")
    , @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword")
    , @NamedQuery(name = "Users.findByJobRole", query = "SELECT u FROM Users u WHERE u.jobRole = :jobRole")
    , @NamedQuery(name = "Users.findByPhoneNumber", query = "SELECT u FROM Users u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 2147483647)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 2147483647)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 2147483647)
    @Column(name = "user_password")
    private String userPassword;
    @Size(max = 2147483647)
    @Column(name = "job_role")
    private String jobRole;
    @Size(max = 2147483647)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "enabled")
    private Boolean enabled;
    @OneToMany(mappedBy = "acknowledgedBy")
    private Collection<AlarmList> alarmListCollection;
    @OneToMany(mappedBy = "commentBy")
    private Collection<AlarmList> alarmListCollection1;
    @OneToMany(mappedBy = "ttBy")
    private Collection<AlarmList> alarmListCollection2;
    @OneToMany(mappedBy = "userId")
    private Collection<UsersViews> usersViewsCollection;
    @OneToMany(mappedBy = "acknowledgedBy")
    private Collection<AlarmLog> alarmLogCollection;
    @OneToMany(mappedBy = "commentBy")
    private Collection<AlarmLog> alarmLogCollection1;
    @OneToMany(mappedBy = "ttBy")
    private Collection<AlarmLog> alarmLogCollection2;

    public Users() {
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @XmlTransient
    public Collection<AlarmList> getAlarmListCollection() {
        return alarmListCollection;
    }

    public void setAlarmListCollection(Collection<AlarmList> alarmListCollection) {
        this.alarmListCollection = alarmListCollection;
    }

    @XmlTransient
    public Collection<AlarmList> getAlarmListCollection1() {
        return alarmListCollection1;
    }

    public void setAlarmListCollection1(Collection<AlarmList> alarmListCollection1) {
        this.alarmListCollection1 = alarmListCollection1;
    }

    @XmlTransient
    public Collection<AlarmList> getAlarmListCollection2() {
        return alarmListCollection2;
    }

    public void setAlarmListCollection2(Collection<AlarmList> alarmListCollection2) {
        this.alarmListCollection2 = alarmListCollection2;
    }

    @XmlTransient
    public Collection<UsersViews> getUsersViewsCollection() {
        return usersViewsCollection;
    }

    public void setUsersViewsCollection(Collection<UsersViews> usersViewsCollection) {
        this.usersViewsCollection = usersViewsCollection;
    }

    @XmlTransient
    public Collection<AlarmLog> getAlarmLogCollection() {
        return alarmLogCollection;
    }

    public void setAlarmLogCollection(Collection<AlarmLog> alarmLogCollection) {
        this.alarmLogCollection = alarmLogCollection;
    }

    @XmlTransient
    public Collection<AlarmLog> getAlarmLogCollection1() {
        return alarmLogCollection1;
    }

    public void setAlarmLogCollection1(Collection<AlarmLog> alarmLogCollection1) {
        this.alarmLogCollection1 = alarmLogCollection1;
    }

    @XmlTransient
    public Collection<AlarmLog> getAlarmLogCollection2() {
        return alarmLogCollection2;
    }

    public void setAlarmLogCollection2(Collection<AlarmLog> alarmLogCollection2) {
        this.alarmLogCollection2 = alarmLogCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.fm.entities.Users[ userName=" + userName + " ]";
    }
    
}
