/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<AssignmentGroups> assignmentGroupsCollection;
    @OneToMany(mappedBy = "uploadedBy")
    private Collection<TroubleTicketAttachments> troubleTicketAttachmentsCollection;
    @OneToMany(mappedBy = "commentBy")
    private Collection<TroubleTicketLog> troubleTicketLogCollection;
    @OneToMany(mappedBy = "resolvedBy")
    private Collection<TroubleTickets> troubleTicketsCollection;
    @OneToMany(mappedBy = "ttCreator")
    private Collection<TroubleTickets> troubleTicketsCollection1;
    @OneToMany(mappedBy = "groupOwner")
    private Collection<AssignmentGroups> assignmentGroupsCollection1;
    @OneToMany(mappedBy = "involvmentBy")
    private Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<UserRoles> userRolesCollection;

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
    public Collection<AssignmentGroups> getAssignmentGroupsCollection() {
        return assignmentGroupsCollection;
    }

    public void setAssignmentGroupsCollection(Collection<AssignmentGroups> assignmentGroupsCollection) {
        this.assignmentGroupsCollection = assignmentGroupsCollection;
    }

    @XmlTransient
    public Collection<TroubleTicketAttachments> getTroubleTicketAttachmentsCollection() {
        return troubleTicketAttachmentsCollection;
    }

    public void setTroubleTicketAttachmentsCollection(Collection<TroubleTicketAttachments> troubleTicketAttachmentsCollection) {
        this.troubleTicketAttachmentsCollection = troubleTicketAttachmentsCollection;
    }

    @XmlTransient
    public Collection<TroubleTicketLog> getTroubleTicketLogCollection() {
        return troubleTicketLogCollection;
    }

    public void setTroubleTicketLogCollection(Collection<TroubleTicketLog> troubleTicketLogCollection) {
        this.troubleTicketLogCollection = troubleTicketLogCollection;
    }

    @XmlTransient
    public Collection<TroubleTickets> getTroubleTicketsCollection() {
        return troubleTicketsCollection;
    }

    public void setTroubleTicketsCollection(Collection<TroubleTickets> troubleTicketsCollection) {
        this.troubleTicketsCollection = troubleTicketsCollection;
    }

    @XmlTransient
    public Collection<TroubleTickets> getTroubleTicketsCollection1() {
        return troubleTicketsCollection1;
    }

    public void setTroubleTicketsCollection1(Collection<TroubleTickets> troubleTicketsCollection1) {
        this.troubleTicketsCollection1 = troubleTicketsCollection1;
    }

    @XmlTransient
    public Collection<AssignmentGroups> getAssignmentGroupsCollection1() {
        return assignmentGroupsCollection1;
    }

    public void setAssignmentGroupsCollection1(Collection<AssignmentGroups> assignmentGroupsCollection1) {
        this.assignmentGroupsCollection1 = assignmentGroupsCollection1;
    }

    @XmlTransient
    public Collection<TroubleTicketInvolvment> getTroubleTicketInvolvmentCollection() {
        return troubleTicketInvolvmentCollection;
    }

    public void setTroubleTicketInvolvmentCollection(Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection) {
        this.troubleTicketInvolvmentCollection = troubleTicketInvolvmentCollection;
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
        return "com.te.tt.entities.Users[ userName=" + userName + " ]";
    }
    
    @XmlTransient
    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }
}
