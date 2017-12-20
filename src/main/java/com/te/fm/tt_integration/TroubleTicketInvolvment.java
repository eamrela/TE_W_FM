/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "trouble_ticket_involvment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketInvolvment.findAll", query = "SELECT t FROM TroubleTicketInvolvment t")
    , @NamedQuery(name = "TroubleTicketInvolvment.findByInvolvmentId", query = "SELECT t FROM TroubleTicketInvolvment t WHERE t.involvmentId = :involvmentId")
    , @NamedQuery(name = "TroubleTicketInvolvment.findByInvolvmentType", query = "SELECT t FROM TroubleTicketInvolvment t WHERE t.involvmentType = :involvmentType")
    , @NamedQuery(name = "TroubleTicketInvolvment.findByInvolvmentTime", query = "SELECT t FROM TroubleTicketInvolvment t WHERE t.involvmentTime = :involvmentTime")})
public class TroubleTicketInvolvment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "involvment_id")
    private Long involvmentId;
    @Size(max = 2147483647)
    @Column(name = "involvment_type")
    private String involvmentType;
    @Column(name = "involvment_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date involvmentTime;
    @JoinColumn(name = "current_group", referencedColumnName = "group_name")
    @ManyToOne
    private AssignmentGroups currentGroup;
    @JoinColumn(name = "previous_group", referencedColumnName = "group_name")
    @ManyToOne
    private AssignmentGroups previousGroup;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;
    @JoinColumn(name = "involvment_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users involvmentBy;

    public TroubleTicketInvolvment() {
    }

    public TroubleTicketInvolvment(Long involvmentId) {
        this.involvmentId = involvmentId;
    }

    public Long getInvolvmentId() {
        return involvmentId;
    }

    public void setInvolvmentId(Long involvmentId) {
        this.involvmentId = involvmentId;
    }

    public String getInvolvmentType() {
        return involvmentType;
    }

    public void setInvolvmentType(String involvmentType) {
        this.involvmentType = involvmentType;
    }

    public Date getInvolvmentTime() {
        return involvmentTime;
    }

    public void setInvolvmentTime(Date involvmentTime) {
        this.involvmentTime = involvmentTime;
    }

    public AssignmentGroups getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(AssignmentGroups currentGroup) {
        this.currentGroup = currentGroup;
    }

    public AssignmentGroups getPreviousGroup() {
        return previousGroup;
    }

    public void setPreviousGroup(AssignmentGroups previousGroup) {
        this.previousGroup = previousGroup;
    }

    public TroubleTickets getTtId() {
        return ttId;
    }

    public void setTtId(TroubleTickets ttId) {
        this.ttId = ttId;
    }

    public Users getInvolvmentBy() {
        return involvmentBy;
    }

    public void setInvolvmentBy(Users involvmentBy) {
        this.involvmentBy = involvmentBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (involvmentId != null ? involvmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketInvolvment)) {
            return false;
        }
        TroubleTicketInvolvment other = (TroubleTicketInvolvment) object;
        if ((this.involvmentId == null && other.involvmentId != null) || (this.involvmentId != null && !this.involvmentId.equals(other.involvmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketInvolvment[ involvmentId=" + involvmentId + " ]";
    }
    
}
