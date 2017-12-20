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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "assignment_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssignmentGroups.findAll", query = "SELECT a FROM AssignmentGroups a")
    , @NamedQuery(name = "AssignmentGroups.findByGroupName", query = "SELECT a FROM AssignmentGroups a WHERE a.groupName = :groupName")})
public class AssignmentGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "group_name")
    private String groupName;
    @JoinTable(name = "users_j_groups", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "group_name")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_name")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "ttAssignmentGroup")
    private Collection<TroubleTickets> troubleTicketsCollection;
    @JoinColumn(name = "group_owner", referencedColumnName = "user_name")
    @ManyToOne
    private Users groupOwner;
    @OneToMany(mappedBy = "currentGroup")
    private Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection;
    @OneToMany(mappedBy = "previousGroup")
    private Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection1;

    public AssignmentGroups() {
    }

    public AssignmentGroups(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<TroubleTickets> getTroubleTicketsCollection() {
        return troubleTicketsCollection;
    }

    public void setTroubleTicketsCollection(Collection<TroubleTickets> troubleTicketsCollection) {
        this.troubleTicketsCollection = troubleTicketsCollection;
    }

    public Users getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(Users groupOwner) {
        this.groupOwner = groupOwner;
    }

    @XmlTransient
    public Collection<TroubleTicketInvolvment> getTroubleTicketInvolvmentCollection() {
        return troubleTicketInvolvmentCollection;
    }

    public void setTroubleTicketInvolvmentCollection(Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection) {
        this.troubleTicketInvolvmentCollection = troubleTicketInvolvmentCollection;
    }

    @XmlTransient
    public Collection<TroubleTicketInvolvment> getTroubleTicketInvolvmentCollection1() {
        return troubleTicketInvolvmentCollection1;
    }

    public void setTroubleTicketInvolvmentCollection1(Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection1) {
        this.troubleTicketInvolvmentCollection1 = troubleTicketInvolvmentCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupName != null ? groupName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssignmentGroups)) {
            return false;
        }
        AssignmentGroups other = (AssignmentGroups) object;
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.AssignmentGroups[ groupName=" + groupName + " ]";
    }
    
}
