/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "customer_owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOwner.findAll", query = "SELECT c FROM CustomerOwner c")
    , @NamedQuery(name = "CustomerOwner.findById", query = "SELECT c FROM CustomerOwner c WHERE c.id = :id")
    , @NamedQuery(name = "CustomerOwner.findByFullName", query = "SELECT c FROM CustomerOwner c WHERE c.fullName = :fullName")
    , @NamedQuery(name = "CustomerOwner.findByEmailAddress", query = "SELECT c FROM CustomerOwner c WHERE c.emailAddress = :emailAddress")
    , @NamedQuery(name = "CustomerOwner.findByPhoneNumber", query = "SELECT c FROM CustomerOwner c WHERE c.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "CustomerOwner.findByJobRole", query = "SELECT c FROM CustomerOwner c WHERE c.jobRole = :jobRole")})
public class CustomerOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 2147483647)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 2147483647)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 2147483647)
    @Column(name = "job_role")
    private String jobRole;

    public CustomerOwner() {
    }

    public CustomerOwner(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOwner)) {
            return false;
        }
        CustomerOwner other = (CustomerOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.CustomerOwner[ id=" + id + " ]";
    }
    
}
