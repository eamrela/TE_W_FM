/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "domains")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domains.findAll", query = "SELECT d FROM Domains d")
    , @NamedQuery(name = "Domains.findByDomainName", query = "SELECT d FROM Domains d WHERE d.domainName = :domainName")
    , @NamedQuery(name = "Domains.findByDomainOwner", query = "SELECT d FROM Domains d WHERE d.domainOwner = :domainOwner")})
public class Domains implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "domain_name")
    private String domainName;
    @Size(max = 2147483647)
    @Column(name = "domain_owner")
    private String domainOwner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domains")
    private Collection<Nodes> nodesCollection;
    @OneToMany(mappedBy = "ttDomain")
    private Collection<TroubleTickets> troubleTicketsCollection;

    public Domains() {
    }

    public Domains(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainOwner() {
        return domainOwner;
    }

    public void setDomainOwner(String domainOwner) {
        this.domainOwner = domainOwner;
    }

    @XmlTransient
    public Collection<Nodes> getNodesCollection() {
        return nodesCollection;
    }

    public void setNodesCollection(Collection<Nodes> nodesCollection) {
        this.nodesCollection = nodesCollection;
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
        hash += (domainName != null ? domainName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domains)) {
            return false;
        }
        Domains other = (Domains) object;
        if ((this.domainName == null && other.domainName != null) || (this.domainName != null && !this.domainName.equals(other.domainName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return domainName;
    }
    
}
