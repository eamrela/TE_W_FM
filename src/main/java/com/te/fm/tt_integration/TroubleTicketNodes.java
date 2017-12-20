/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "trouble_ticket_nodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketNodes.findAll", query = "SELECT t FROM TroubleTicketNodes t")
    , @NamedQuery(name = "TroubleTicketNodes.findByNodeTtId", query = "SELECT t FROM TroubleTicketNodes t WHERE t.nodeTtId = :nodeTtId")})
public class TroubleTicketNodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "node_tt_id")
    private Long nodeTtId;
    @JoinColumns({
        @JoinColumn(name = "node_name", referencedColumnName = "node_name")
        , @JoinColumn(name = "domain_name", referencedColumnName = "node_domain")})
    @ManyToOne
    private Nodes nodes;
    @JoinColumn(name = "tt_id", referencedColumnName = "tt_id")
    @ManyToOne
    private TroubleTickets ttId;

    public TroubleTicketNodes() {
    }

    public TroubleTicketNodes(Long nodeTtId) {
        this.nodeTtId = nodeTtId;
    }

    public Long getNodeTtId() {
        return nodeTtId;
    }

    public void setNodeTtId(Long nodeTtId) {
        this.nodeTtId = nodeTtId;
    }

    public Nodes getNodes() {
        return nodes;
    }

    public void setNodes(Nodes nodes) {
        this.nodes = nodes;
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
        hash += (nodeTtId != null ? nodeTtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TroubleTicketNodes)) {
            return false;
        }
        TroubleTicketNodes other = (TroubleTicketNodes) object;
        if ((this.nodeTtId == null && other.nodeTtId != null) || (this.nodeTtId != null && !this.nodeTtId.equals(other.nodeTtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketNodes[ nodeTtId=" + nodeTtId + " ]";
    }
    
}
