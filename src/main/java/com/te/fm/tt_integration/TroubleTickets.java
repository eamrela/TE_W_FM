/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "trouble_tickets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTickets.findAll", query = "SELECT t FROM TroubleTickets t")
    , @NamedQuery(name = "TroubleTickets.findByTtId", query = "SELECT t FROM TroubleTickets t WHERE t.ttId = :ttId")
    , @NamedQuery(name = "TroubleTickets.findByTtDescription", query = "SELECT t FROM TroubleTickets t WHERE t.ttDescription = :ttDescription")
    , @NamedQuery(name = "TroubleTickets.findByTtTitle", query = "SELECT t FROM TroubleTickets t WHERE t.ttTitle = :ttTitle")
    , @NamedQuery(name = "TroubleTickets.findByTtSubcategory", query = "SELECT t FROM TroubleTickets t WHERE t.ttSubcategory = :ttSubcategory")
    , @NamedQuery(name = "TroubleTickets.findByTtCreationDate", query = "SELECT t FROM TroubleTickets t WHERE t.ttCreationDate = :ttCreationDate")
    , @NamedQuery(name = "TroubleTickets.findByTtLastModificationDate", query = "SELECT t FROM TroubleTickets t WHERE t.ttLastModificationDate = :ttLastModificationDate")})
public class TroubleTickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tt_id")
    private Long ttId;
    @Size(max = 2147483647)
    @Column(name = "tt_description")
    private String ttDescription;
    @Size(max = 2147483647)
    @Column(name = "resolution_action")
    private String resolutionAction;
    @Size(max = 2147483647)
    @Column(name = "tt_title")
    private String ttTitle;
    @Size(max = 2147483647)
    @Column(name = "tt_subcategory")
    private String ttSubcategory;
    @Column(name = "tt_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttCreationDate;
    @Column(name = "tt_last_modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttLastModificationDate;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketAttachments> troubleTicketAttachmentsCollection;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketLog> troubleTicketLogCollection;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketNodes> troubleTicketNodesCollection;
    @JoinColumn(name = "tt_assignment_group", referencedColumnName = "group_name")
    @ManyToOne
    private AssignmentGroups ttAssignmentGroup;
    @JoinColumn(name = "tt_initator_group", referencedColumnName = "group_name")
    @ManyToOne
    private AssignmentGroups ttInitatorGroup;
    @JoinColumn(name = "tt_category", referencedColumnName = "category_name")
    @ManyToOne
    private Categories ttCategory;
    @JoinColumn(name = "tt_domain", referencedColumnName = "domain_name")
    @ManyToOne
    private Domains ttDomain;
    @JoinColumn(name = "tt_priority", referencedColumnName = "priority_name")
    @ManyToOne
    private TroubleTicketPriority ttPriority;
    @JoinColumn(name = "tt_status", referencedColumnName = "status_name")
    @ManyToOne
    private TroubleTicketStatus ttStatus;
    @JoinColumn(name = "resolved_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users resolvedBy;
    @JoinColumn(name = "tt_creator", referencedColumnName = "user_name")
    @ManyToOne
    private Users ttCreator;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection;
    @Size(max = 2147483647)
    @Column(name = "rejection_reason")
    private String rejectionReason;
    @Column(name = "rejection_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectionTime;
    @Column(name = "resolve_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolveTime;
    @Column(name = "event_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    @Size(max = 2147483647)
    @Column(name = "external_tt_id")
    private String externalTtId;
    @Column(name = "close_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;
    @Column(name = "real_close_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date realCloseTime;
    @Column(name = "major_incident")
    private Boolean majorIncident;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketSla> troubleTicketSlaCollection;
    @JoinColumn(name = "closed_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users closedBy;
    @JoinColumn(name = "area", referencedColumnName = "area_name")
    @ManyToOne
    private Areas area;
    @JoinColumn(name = "rejected_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users rejectedBy;
    @OneToMany(mappedBy = "ttId")
    private Collection<TroubleTicketsAlarms> troubleTicketsAlarmsCollection;

    public TroubleTickets() {
    }

    public TroubleTickets(Long ttId) {
        this.ttId = ttId;
    }

    public Long getTtId() {
        return ttId;
    }

    public String getResolutionAction() {
        return resolutionAction;
    }

    public void setResolutionAction(String resolutionAction) {
        this.resolutionAction = resolutionAction;
    }

    
    public void setTtId(Long ttId) {
        this.ttId = ttId;
    }

    public String getTtDescription() {
        return ttDescription;
    }

    public void setTtDescription(String ttDescription) {
        this.ttDescription = ttDescription;
    }

    public String getTtTitle() {
        return ttTitle;
    }

    public void setTtTitle(String ttTitle) {
        this.ttTitle = ttTitle;
    }

    public String getTtSubcategory() {
        return ttSubcategory;
    }

    public void setTtSubcategory(String ttSubcategory) {
        this.ttSubcategory = ttSubcategory;
    }

    public Date getTtCreationDate() {
        return ttCreationDate;
    }

    public void setTtCreationDate(Date ttCreationDate) {
        this.ttCreationDate = ttCreationDate;
    }

    public Date getTtLastModificationDate() {
        return ttLastModificationDate;
    }

    public void setTtLastModificationDate(Date ttLastModificationDate) {
        this.ttLastModificationDate = ttLastModificationDate;
    }

    @XmlTransient
    public Collection<TroubleTicketAttachments> getTroubleTicketAttachmentsCollection() {
        return troubleTicketAttachmentsCollection;
    }

    public void setTroubleTicketAttachmentsCollection(Collection<TroubleTicketAttachments> troubleTicketAttachmentsCollection) {
        this.troubleTicketAttachmentsCollection = troubleTicketAttachmentsCollection;
    }

    @XmlTransient
    public Collection<TroubleTicketLog> getTroubleTicketLogCollection() {
        return troubleTicketLogCollection;
    }

    public void setTroubleTicketLogCollection(Collection<TroubleTicketLog> troubleTicketLogCollection) {
        this.troubleTicketLogCollection = troubleTicketLogCollection;
    }

    @XmlTransient
    public Collection<TroubleTicketNodes> getTroubleTicketNodesCollection() {
        return troubleTicketNodesCollection;
    }

    public void setTroubleTicketNodesCollection(Collection<TroubleTicketNodes> troubleTicketNodesCollection) {
        this.troubleTicketNodesCollection = troubleTicketNodesCollection;
    }

    public AssignmentGroups getTtAssignmentGroup() {
        return ttAssignmentGroup;
    }

    public void setTtAssignmentGroup(AssignmentGroups ttAssignmentGroup) {
        this.ttAssignmentGroup = ttAssignmentGroup;
    }

    public Categories getTtCategory() {
        return ttCategory;
    }

    public void setTtCategory(Categories ttCategory) {
        this.ttCategory = ttCategory;
    }

    public Domains getTtDomain() {
        return ttDomain;
    }

    public void setTtDomain(Domains ttDomain) {
        this.ttDomain = ttDomain;
    }

    public TroubleTicketPriority getTtPriority() {
        return ttPriority;
    }

    public void setTtPriority(TroubleTicketPriority ttPriority) {
        this.ttPriority = ttPriority;
    }

    public TroubleTicketStatus getTtStatus() {
        return ttStatus;
    }

    public void setTtStatus(TroubleTicketStatus ttStatus) {
        this.ttStatus = ttStatus;
    }

    public Users getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(Users resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public Users getTtCreator() {
        return ttCreator;
    }

    public void setTtCreator(Users ttCreator) {
        this.ttCreator = ttCreator;
    }

    @XmlTransient
    public Collection<TroubleTicketInvolvment> getTroubleTicketInvolvmentCollection() {
        return troubleTicketInvolvmentCollection;
    }

    public void setTroubleTicketInvolvmentCollection(Collection<TroubleTicketInvolvment> troubleTicketInvolvmentCollection) {
        this.troubleTicketInvolvmentCollection = troubleTicketInvolvmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ttId != null ? ttId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTickets)) {
            return false;
        }
        TroubleTickets other = (TroubleTickets) object;
        if ((this.ttId == null && other.ttId != null) || (this.ttId != null && !this.ttId.equals(other.ttId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTickets[ ttId=" + ttId + " ]";
    }

    public AssignmentGroups getTtInitatorGroup() {
        return ttInitatorGroup;
    }

    public void setTtInitatorGroup(AssignmentGroups ttInitatorGroup) {
        this.ttInitatorGroup = ttInitatorGroup;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Date getRejectionTime() {
        return rejectionTime;
    }

    public void setRejectionTime(Date rejectionTime) {
        this.rejectionTime = rejectionTime;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getExternalTtId() {
        return externalTtId;
    }

    public void setExternalTtId(String externalTtId) {
        this.externalTtId = externalTtId;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getRealCloseTime() {
        return realCloseTime;
    }

    public void setRealCloseTime(Date realCloseTime) {
        this.realCloseTime = realCloseTime;
    }

    public Boolean getMajorIncident() {
        return majorIncident;
    }

    public void setMajorIncident(Boolean majorIncident) {
        this.majorIncident = majorIncident;
    }
    @XmlTransient
    public Collection<TroubleTicketSla> getTroubleTicketSlaCollection() {
        return troubleTicketSlaCollection;
    }

    public void setTroubleTicketSlaCollection(Collection<TroubleTicketSla> troubleTicketSlaCollection) {
        this.troubleTicketSlaCollection = troubleTicketSlaCollection;
    }

    public Users getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Users closedBy) {
        this.closedBy = closedBy;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public Users getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(Users rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public Date getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(Date resolveTime) {
        this.resolveTime = resolveTime;
    }
    
    @XmlTransient
    public Collection<TroubleTicketsAlarms> getTroubleTicketsAlarmsCollection() {
        return troubleTicketsAlarmsCollection;
    }

    public void setTroubleTicketsAlarmsCollection(Collection<TroubleTicketsAlarms> troubleTicketsAlarmsCollection) {
        this.troubleTicketsAlarmsCollection = troubleTicketsAlarmsCollection;
    }
    
}
