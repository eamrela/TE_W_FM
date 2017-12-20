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
@Table(name = "trouble_ticket_priority")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketPriority.findAll", query = "SELECT t FROM TroubleTicketPriority t")
    , @NamedQuery(name = "TroubleTicketPriority.findByPriorityName", query = "SELECT t FROM TroubleTicketPriority t WHERE t.priorityName = :priorityName")})
public class TroubleTicketPriority implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "priority_name")
    private String priorityName;
    @OneToMany(mappedBy = "ttPriority")
    private Collection<TroubleTickets> troubleTicketsCollection;

    public TroubleTicketPriority() {
    }

    public TroubleTicketPriority(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    @XmlTransient
    public Collection<TroubleTickets> getTroubleTicketsCollection() {
        return troubleTicketsCollection;
    }

    public void setTroubleTicketsCollection(Collection<TroubleTickets> troubleTicketsCollection) {
        this.troubleTicketsCollection = troubleTicketsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priorityName != null ? priorityName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketPriority)) {
            return false;
        }
        TroubleTicketPriority other = (TroubleTicketPriority) object;
        if ((this.priorityName == null && other.priorityName != null) || (this.priorityName != null && !this.priorityName.equals(other.priorityName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketPriority[ priorityName=" + priorityName + " ]";
    }
    
}
