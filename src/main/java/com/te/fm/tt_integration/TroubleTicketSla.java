/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "trouble_ticket_sla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketSla.findAll", query = "SELECT t FROM TroubleTicketSla t")
    , @NamedQuery(name = "TroubleTicketSla.findById", query = "SELECT t FROM TroubleTicketSla t WHERE t.id = :id")
    , @NamedQuery(name = "TroubleTicketSla.findByTargetSla", query = "SELECT t FROM TroubleTicketSla t WHERE t.targetSla = :targetSla")
    , @NamedQuery(name = "TroubleTicketSla.findBySla15", query = "SELECT t FROM TroubleTicketSla t WHERE t.sla15 = :sla15")
    , @NamedQuery(name = "TroubleTicketSla.findBySla3", query = "SELECT t FROM TroubleTicketSla t WHERE t.sla3 = :sla3")
    , @NamedQuery(name = "TroubleTicketSla.findByMttr", query = "SELECT t FROM TroubleTicketSla t WHERE t.mttr = :mttr")
    , @NamedQuery(name = "TroubleTicketSla.findBySlaStatus", query = "SELECT t FROM TroubleTicketSla t WHERE t.slaStatus = :slaStatus")})
public class TroubleTicketSla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "target_sla")
    private BigInteger targetSla;
    @Column(name = "sla_15")
    private BigInteger sla15;
    @Column(name = "sla_3")
    private BigInteger sla3;
    @Column(name = "mttr")
    private BigInteger mttr;
    @Size(max = 2147483647)
    @Column(name = "sla_status")
    private String slaStatus;
    @JoinColumn(name = "sla_id", referencedColumnName = "sla_name")
    @ManyToOne
    private SlaRules slaId;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;

    public TroubleTicketSla() {
    }

    public TroubleTicketSla(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getTargetSla() {
        return targetSla;
    }

    public void setTargetSla(BigInteger targetSla) {
        this.targetSla = targetSla;
    }

    public BigInteger getSla15() {
        return sla15;
    }

    public void setSla15(BigInteger sla15) {
        this.sla15 = sla15;
    }

    public BigInteger getSla3() {
        return sla3;
    }

    public void setSla3(BigInteger sla3) {
        this.sla3 = sla3;
    }

    public BigInteger getMttr() {
        return mttr;
    }

    public void setMttr(BigInteger mttr) {
        this.mttr = mttr;
    }

    public String getSlaStatus() {
        return slaStatus;
    }

    public void setSlaStatus(String slaStatus) {
        this.slaStatus = slaStatus;
    }

    public SlaRules getSlaId() {
        return slaId;
    }

    public void setSlaId(SlaRules slaId) {
        this.slaId = slaId;
    }

    public TroubleTickets getTtId() {
        return ttId;
    }

    public void setTtId(TroubleTickets ttId) {
        this.ttId = ttId;
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
        if (!(object instanceof TroubleTicketSla)) {
            return false;
        }
        TroubleTicketSla other = (TroubleTicketSla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vodafone.tett_dummy.TroubleTicketSla[ id=" + id + " ]";
    }
    
}
