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
@Table(name = "trouble_ticket_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketLog.findAll", query = "SELECT t FROM TroubleTicketLog t")
    , @NamedQuery(name = "TroubleTicketLog.findByCommentText", query = "SELECT t FROM TroubleTicketLog t WHERE t.commentText = :commentText")
    , @NamedQuery(name = "TroubleTicketLog.findByCommentTime", query = "SELECT t FROM TroubleTicketLog t WHERE t.commentTime = :commentTime")
    , @NamedQuery(name = "TroubleTicketLog.findByCommentId", query = "SELECT t FROM TroubleTicketLog t WHERE t.commentId = :commentId")})
public class TroubleTicketLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "comment_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_id")
    private Long commentId;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;
    @JoinColumn(name = "comment_by", referencedColumnName = "user_name")
    @ManyToOne
    private Users commentBy;

    public TroubleTicketLog() {
    }

    public TroubleTicketLog(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public TroubleTickets getTtId() {
        return ttId;
    }

    public void setTtId(TroubleTickets ttId) {
        this.ttId = ttId;
    }

    public Users getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(Users commentBy) {
        this.commentBy = commentBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketLog)) {
            return false;
        }
        TroubleTicketLog other = (TroubleTicketLog) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketLog[ commentId=" + commentId + " ]";
    }
    
}
