/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "alarm_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlarmLog.findAll", query = "SELECT a FROM AlarmLog a")
    , @NamedQuery(name = "AlarmLog.findByAlarmId", query = "SELECT a FROM AlarmLog a WHERE a.alarmId = :alarmId")
    , @NamedQuery(name = "AlarmLog.findByRecordId", query = "SELECT a FROM AlarmLog a WHERE a.recordId = :recordId")
    , @NamedQuery(name = "AlarmLog.findByAcknowledged", query = "SELECT a FROM AlarmLog a WHERE a.acknowledged = :acknowledged")
    , @NamedQuery(name = "AlarmLog.findByAdditionalText", query = "SELECT a FROM AlarmLog a WHERE a.additionalText = :additionalText")
    , @NamedQuery(name = "AlarmLog.findByAlarmType", query = "SELECT a FROM AlarmLog a WHERE a.alarmType = :alarmType")
    , @NamedQuery(name = "AlarmLog.findByAlarmDomain", query = "SELECT a FROM AlarmLog a WHERE a.alarmDomain = :alarmDomain")
    , @NamedQuery(name = "AlarmLog.findByManagedObject", query = "SELECT a FROM AlarmLog a WHERE a.managedObject = :managedObject")
    , @NamedQuery(name = "AlarmLog.findByAlarmNode", query = "SELECT a FROM AlarmLog a WHERE a.alarmNode = :alarmNode")
    , @NamedQuery(name = "AlarmLog.findByAlarmSeverity", query = "SELECT a FROM AlarmLog a WHERE a.alarmSeverity = :alarmSeverity")
    , @NamedQuery(name = "AlarmLog.findBySpecificProblem", query = "SELECT a FROM AlarmLog a WHERE a.specificProblem = :specificProblem")
    , @NamedQuery(name = "AlarmLog.findByEventTime", query = "SELECT a FROM AlarmLog a WHERE a.eventTime = :eventTime")
    , @NamedQuery(name = "AlarmLog.findByCeaseTime", query = "SELECT a FROM AlarmLog a WHERE a.ceaseTime = :ceaseTime")
    , @NamedQuery(name = "AlarmLog.findByAcknowledgeTime", query = "SELECT a FROM AlarmLog a WHERE a.acknowledgeTime = :acknowledgeTime")
    , @NamedQuery(name = "AlarmLog.findByAlarmComment", query = "SELECT a FROM AlarmLog a WHERE a.alarmComment = :alarmComment")
    , @NamedQuery(name = "AlarmLog.findByServiceAffecting", query = "SELECT a FROM AlarmLog a WHERE a.serviceAffecting = :serviceAffecting")
    , @NamedQuery(name = "AlarmLog.findByProbableCause", query = "SELECT a FROM AlarmLog a WHERE a.probableCause = :probableCause")
    , @NamedQuery(name = "AlarmLog.findByLoggingTime", query = "SELECT a FROM AlarmLog a WHERE a.loggingTime = :loggingTime")
    , @NamedQuery(name = "AlarmLog.findByTtId", query = "SELECT a FROM AlarmLog a WHERE a.ttId = :ttId")})
public class AlarmLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "alarm_id")
    private String alarmId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "record_id")
    private Long recordId;
    @Column(name = "acknowledged")
    private Boolean acknowledged;
    @Size(max = 2147483647)
    @Column(name = "additional_text")
    private String additionalText;
    @Size(max = 2147483647)
    @Column(name = "alarm_type")
    private String alarmType;
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
    @Column(name = "acknowledge_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acknowledgeTime;
    @Size(max = 2147483647)
    @Column(name = "alarm_comment")
    private String alarmComment;
    @Column(name = "service_affecting")
    private Boolean serviceAffecting;
    @Size(max = 2147483647)
    @Column(name = "probable_cause")
    private String probableCause;
    @Column(name = "logging_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggingTime;
    @Column(name = "tt_id")
    private BigInteger ttId;
    @JoinColumn(name = "alarm_oc", referencedColumnName = "oc_name")
    @ManyToOne
    private OperationalContext alarmOc;
    @JoinColumn(name = "acknowledged_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users acknowledgedBy;
    @JoinColumn(name = "comment_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users commentBy;
    @JoinColumn(name = "tt_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users ttBy;

    public AlarmLog() {
    }

    public AlarmLog(Long recordId) {
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

    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
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

    public Date getAcknowledgeTime() {
        return acknowledgeTime;
    }

    public void setAcknowledgeTime(Date acknowledgeTime) {
        this.acknowledgeTime = acknowledgeTime;
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

    public Date getLoggingTime() {
        return loggingTime;
    }

    public void setLoggingTime(Date loggingTime) {
        this.loggingTime = loggingTime;
    }

    public BigInteger getTtId() {
        return ttId;
    }

    public void setTtId(BigInteger ttId) {
        this.ttId = ttId;
    }

    public OperationalContext getAlarmOc() {
        return alarmOc;
    }

    public void setAlarmOc(OperationalContext alarmOc) {
        this.alarmOc = alarmOc;
    }

    public Users getAcknowledgedBy() {
        return acknowledgedBy;
    }

    public void setAcknowledgedBy(Users acknowledgedBy) {
        this.acknowledgedBy = acknowledgedBy;
    }

    public Users getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(Users commentBy) {
        this.commentBy = commentBy;
    }

    public Users getTtBy() {
        return ttBy;
    }

    public void setTtBy(Users ttBy) {
        this.ttBy = ttBy;
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
        if (!(object instanceof AlarmLog)) {
            return false;
        }
        AlarmLog other = (AlarmLog) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.fm.entities.AlarmLog[ recordId=" + recordId + " ]";
    }
    
}
