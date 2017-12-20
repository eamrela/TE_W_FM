/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "sla_rules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SlaRules.findAll", query = "SELECT s FROM SlaRules s")
    , @NamedQuery(name = "SlaRules.findBySlaName", query = "SELECT s FROM SlaRules s WHERE s.slaName = :slaName")
    , @NamedQuery(name = "SlaRules.findByPriorityCondition", query = "SELECT s FROM SlaRules s WHERE s.priorityCondition = :priorityCondition")
    , @NamedQuery(name = "SlaRules.findBySeverityCondition", query = "SELECT s FROM SlaRules s WHERE s.severityCondition = :severityCondition")
    , @NamedQuery(name = "SlaRules.findByCategoryCondition", query = "SELECT s FROM SlaRules s WHERE s.categoryCondition = :categoryCondition")
    , @NamedQuery(name = "SlaRules.findByServiceCondition", query = "SELECT s FROM SlaRules s WHERE s.serviceCondition = :serviceCondition")
    , @NamedQuery(name = "SlaRules.findByTargetSla", query = "SELECT s FROM SlaRules s WHERE s.targetSla = :targetSla")})
public class SlaRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sla_name")
    private String slaName;
    @Size(max = 2147483647)
    @Column(name = "priority_condition")
    private String priorityCondition;
    @Size(max = 2147483647)
    @Column(name = "severity_condition")
    private String severityCondition;
    @Size(max = 2147483647)
    @Column(name = "category_condition")
    private String categoryCondition;
    @Size(max = 2147483647)
    @Column(name = "service_condition")
    private String serviceCondition;
    @Column(name = "target_sla")
    private BigInteger targetSla;
    @OneToMany(mappedBy = "slaId")
    private Collection<TroubleTicketSla> troubleTicketSlaCollection;

    public SlaRules() {
    }

    public SlaRules(String slaName) {
        this.slaName = slaName;
    }

    public String getSlaName() {
        return slaName;
    }

    public void setSlaName(String slaName) {
        this.slaName = slaName;
    }

    public String getPriorityCondition() {
        return priorityCondition;
    }

    public void setPriorityCondition(String priorityCondition) {
        this.priorityCondition = priorityCondition;
    }

    public String getSeverityCondition() {
        return severityCondition;
    }

    public void setSeverityCondition(String severityCondition) {
        this.severityCondition = severityCondition;
    }

    public String getCategoryCondition() {
        return categoryCondition;
    }

    public void setCategoryCondition(String categoryCondition) {
        this.categoryCondition = categoryCondition;
    }

    public String getServiceCondition() {
        return serviceCondition;
    }

    public void setServiceCondition(String serviceCondition) {
        this.serviceCondition = serviceCondition;
    }

    public BigInteger getTargetSla() {
        return targetSla;
    }

    public void setTargetSla(BigInteger targetSla) {
        this.targetSla = targetSla;
    }

    @XmlTransient
    public Collection<TroubleTicketSla> getTroubleTicketSlaCollection() {
        return troubleTicketSlaCollection;
    }

    public void setTroubleTicketSlaCollection(Collection<TroubleTicketSla> troubleTicketSlaCollection) {
        this.troubleTicketSlaCollection = troubleTicketSlaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slaName != null ? slaName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SlaRules)) {
            return false;
        }
        SlaRules other = (SlaRules) object;
        if ((this.slaName == null && other.slaName != null) || (this.slaName != null && !this.slaName.equals(other.slaName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vodafone.tett_dummy.SlaRules[ slaName=" + slaName + " ]";
    }
    
}
