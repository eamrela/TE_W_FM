/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

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
    , @NamedQuery(name = "Domains.findByDomainName", query = "SELECT d FROM Domains d WHERE d.domainName = :domainName")})
public class Domains implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "domain_name")
    private String domainName;
    @OneToMany(mappedBy = "ocDomain")
    private Collection<OperationalContext> operationalContextCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nodeDomain")
    private Collection<Nodes> nodesCollection;

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

    @XmlTransient
    public Collection<OperationalContext> getOperationalContextCollection() {
        return operationalContextCollection;
    }

    public void setOperationalContextCollection(Collection<OperationalContext> operationalContextCollection) {
        this.operationalContextCollection = operationalContextCollection;
    }

    @XmlTransient
    public Collection<Nodes> getNodesCollection() {
        return nodesCollection;
    }

    public void setNodesCollection(Collection<Nodes> nodesCollection) {
        this.nodesCollection = nodesCollection;
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
        return "com.te.fm.entities.Domains[ domainName=" + domainName + " ]";
    }
    
}
