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
@Table(name = "trouble_ticket_attachments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketAttachments.findAll", query = "SELECT t FROM TroubleTicketAttachments t")
    , @NamedQuery(name = "TroubleTicketAttachments.findByAttachmentId", query = "SELECT t FROM TroubleTicketAttachments t WHERE t.attachmentId = :attachmentId")
    , @NamedQuery(name = "TroubleTicketAttachments.findByFileName", query = "SELECT t FROM TroubleTicketAttachments t WHERE t.fileName = :fileName")
    , @NamedQuery(name = "TroubleTicketAttachments.findByFileLocation", query = "SELECT t FROM TroubleTicketAttachments t WHERE t.fileLocation = :fileLocation")
    , @NamedQuery(name = "TroubleTicketAttachments.findByUploadTime", query = "SELECT t FROM TroubleTicketAttachments t WHERE t.uploadTime = :uploadTime")})
public class TroubleTicketAttachments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "attachment_id")
    private Long attachmentId;
    @Size(max = 2147483647)
    @Column(name = "file_name")
    private String fileName;
    @Size(max = 2147483647)
    @Column(name = "file_location")
    private String fileLocation;
    @Column(name = "upload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users uploadedBy;

    public TroubleTicketAttachments() {
    }

    public TroubleTicketAttachments(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public TroubleTickets getTtId() {
        return ttId;
    }

    public void setTtId(TroubleTickets ttId) {
        this.ttId = ttId;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachmentId != null ? attachmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketAttachments)) {
            return false;
        }
        TroubleTicketAttachments other = (TroubleTicketAttachments) object;
        if ((this.attachmentId == null && other.attachmentId != null) || (this.attachmentId != null && !this.attachmentId.equals(other.attachmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketAttachments[ attachmentId=" + attachmentId + " ]";
    }
    
}
