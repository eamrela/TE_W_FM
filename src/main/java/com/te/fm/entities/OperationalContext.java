/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "operational_context")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperationalContext.findAll", query = "SELECT o FROM OperationalContext o")
    , @NamedQuery(name = "OperationalContext.findByOcName", query = "SELECT o FROM OperationalContext o WHERE o.ocName = :ocName")
    , @NamedQuery(name = "OperationalContext.findByOcDirectory", query = "SELECT o FROM OperationalContext o WHERE o.ocDirectory = :ocDirectory")
    , @NamedQuery(name = "OperationalContext.findByOcStatus", query = "SELECT o FROM OperationalContext o WHERE o.ocStatus = :ocStatus")
    , @NamedQuery(name = "OperationalContext.findByOcLastCollectionDate", query = "SELECT o FROM OperationalContext o WHERE o.ocLastCollectionDate = :ocLastCollectionDate")
    , @NamedQuery(name = "OperationalContext.findByOcType", query = "SELECT o FROM OperationalContext o WHERE o.ocType = :ocType")})
public class OperationalContext implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "oc_name")
    private String ocName;
    @Size(max = 2147483647)
    @Column(name = "oc_directory")
    private String ocDirectory;
    @Size(max = 2147483647)
    @Column(name = "oc_status")
    private String ocStatus;
    @Column(name = "oc_last_collection_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ocLastCollectionDate;
    @Size(max = 2147483647)
    @Column(name = "oc_type")
    private String ocType;
    @JoinColumn(name = "oc_domain", referencedColumnName = "domain_name")
    @ManyToOne
    private Domains ocDomain;
    @OneToMany(mappedBy = "alarmOc")
    private Collection<AlarmList> alarmListCollection;
    @OneToMany(mappedBy = "alarmOc")
    private Collection<AlarmLog> alarmLogCollection;

    public OperationalContext() {
    }

    public OperationalContext(String ocName) {
        this.ocName = ocName;
    }

    public String getOcName() {
        return ocName;
    }

    public void setOcName(String ocName) {
        this.ocName = ocName;
    }

    public String getOcDirectory() {
        return ocDirectory;
    }

    public void setOcDirectory(String ocDirectory) {
        this.ocDirectory = ocDirectory;
    }

    public String getOcStatus() {
        return ocStatus;
    }

    public void setOcStatus(String ocStatus) {
        this.ocStatus = ocStatus;
    }

    public Date getOcLastCollectionDate() {
        return ocLastCollectionDate;
    }

    public void setOcLastCollectionDate(Date ocLastCollectionDate) {
        this.ocLastCollectionDate = ocLastCollectionDate;
    }

    public String getOcType() {
        return ocType;
    }

    public void setOcType(String ocType) {
        this.ocType = ocType;
    }

    public Domains getOcDomain() {
        return ocDomain;
    }

    public void setOcDomain(Domains ocDomain) {
        this.ocDomain = ocDomain;
    }

    @XmlTransient
    public Collection<AlarmList> getAlarmListCollection() {
        return alarmListCollection;
    }

    public void setAlarmListCollection(Collection<AlarmList> alarmListCollection) {
        this.alarmListCollection = alarmListCollection;
    }

    @XmlTransient
    public Collection<AlarmLog> getAlarmLogCollection() {
        return alarmLogCollection;
    }

    public void setAlarmLogCollection(Collection<AlarmLog> alarmLogCollection) {
        this.alarmLogCollection = alarmLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocName != null ? ocName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationalContext)) {
            return false;
        }
        OperationalContext other = (OperationalContext) object;
        if ((this.ocName == null && other.ocName != null) || (this.ocName != null && !this.ocName.equals(other.ocName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.fm.entities.OperationalContext[ ocName=" + ocName + " ]";
    }
    
}
