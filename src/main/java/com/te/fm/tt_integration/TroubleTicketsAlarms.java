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
@Table(name = "trouble_tickets_alarms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketsAlarms.findAll", query = "SELECT t FROM TroubleTicketsAlarms t")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmId", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmId = :alarmId")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByRecordId", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.recordId = :recordId")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAdditionalText", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.additionalText = :additionalText")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmDomain", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmDomain = :alarmDomain")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByManagedObject", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.managedObject = :managedObject")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmNode", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmNode = :alarmNode")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmSeverity", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmSeverity = :alarmSeverity")
    , @NamedQuery(name = "TroubleTicketsAlarms.findBySpecificProblem", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.specificProblem = :specificProblem")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByEventTime", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.eventTime = :eventTime")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByCeaseTime", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.ceaseTime = :ceaseTime")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmComment", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmComment = :alarmComment")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByServiceAffecting", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.serviceAffecting = :serviceAffecting")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByProbableCause", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.probableCause = :probableCause")
    , @NamedQuery(name = "TroubleTicketsAlarms.findByAlarmOc", query = "SELECT t FROM TroubleTicketsAlarms t WHERE t.alarmOc = :alarmOc")})
public class TroubleTicketsAlarms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "alarm_id")
    private String alarmId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "record_id")
    private Long recordId;
    @Size(max = 2147483647)
    @Column(name = "additional_text")
    private String additionalText;
    @Size(max = 2147483647)
    @Column(name = "alarm_domain")
    private String alarmDomain;
    @Size(max = 2147483647)
    @Column(name = "managed_object")
    private String managedObject;
    @Size(max = 2147483647)
    @Column(name = "alarm_node")
    private String alarmNode;
    @Size(max = 2147483647)
    @Column(name = "alarm_severity")
    private String alarmSeverity;
    @Size(max = 2147483647)
    @Column(name = "specific_problem")
    private String specificProblem;
    @Column(name = "event_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    @Column(name = "cease_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ceaseTime;
    @Size(max = 2147483647)
    @Column(name = "alarm_comment")
    private String alarmComment;
    @Column(name = "service_affecting")
    private Boolean serviceAffecting;
    @Size(max = 2147483647)
    @Column(name = "probable_cause")
    private String probableCause;
    @Size(max = 2147483647)
    @Column(name = "alarm_oc")
    private String alarmOc;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;

    public TroubleTicketsAlarms() {
    }

    public TroubleTicketsAlarms(Long recordId) {
        this.recordId = recordId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public String getAlarmDomain() {
        return alarmDomain;
    }

    public void setAlarmDomain(String alarmDomain) {
        this.alarmDomain = alarmDomain;
    }

    public String getManagedObject() {
        return managedObject;
    }

    public void setManagedObject(String managedObject) {
        this.managedObject = managedObject;
    }

    public String getAlarmNode() {
        return alarmNode;
    }

    public void setAlarmNode(String alarmNode) {
        this.alarmNode = alarmNode;
    }

    public String getAlarmSeverity() {
        return alarmSeverity;
    }

    public void setAlarmSeverity(String alarmSeverity) {
        this.alarmSeverity = alarmSeverity;
    }

    public String getSpecificProblem() {
        return specificProblem;
    }

    public void setSpecificProblem(String specificProblem) {
        this.specificProblem = specificProblem;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getCeaseTime() {
        return ceaseTime;
    }

    public void setCeaseTime(Date ceaseTime) {
        this.ceaseTime = ceaseTime;
    }

    public String getAlarmComment() {
        return alarmComment;
    }

    public void setAlarmComment(String alarmComment) {
        this.alarmComment = alarmComment;
    }

    public Boolean getServiceAffecting() {
        return serviceAffecting;
    }

    public void setServiceAffecting(Boolean serviceAffecting) {
        this.serviceAffecting = serviceAffecting;
    }

    public String getProbableCause() {
        return probableCause;
    }

    public void setProbableCause(String probableCause) {
        this.probableCause = probableCause;
    }

    public String getAlarmOc() {
        return alarmOc;
    }

    public void setAlarmOc(String alarmOc) {
        this.alarmOc = alarmOc;
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
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketsAlarms)) {
            return false;
        }
        TroubleTicketsAlarms other = (TroubleTicketsAlarms) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vodafone.tett_dummy.entities.TroubleTicketsAlarms[ recordId=" + recordId + " ]";
    }
    
}
