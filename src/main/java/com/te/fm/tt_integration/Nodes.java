/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "nodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nodes.findAll", query = "SELECT n FROM Nodes n")
    , @NamedQuery(name = "Nodes.findByNodeName", query = "SELECT n FROM Nodes n WHERE n.nodesPK.nodeName = :nodeName")
    , @NamedQuery(name = "Nodes.findByNodeDomain", query = "SELECT n FROM Nodes n WHERE n.nodesPK.nodeDomain = :nodeDomain")
    , @NamedQuery(name = "Nodes.findByNodeLocation", query = "SELECT n FROM Nodes n WHERE n.nodeLocation = :nodeLocation")
    , @NamedQuery(name = "Nodes.findByNodeType", query = "SELECT n FROM Nodes n WHERE n.nodeType = :nodeType")
    , @NamedQuery(name = "Nodes.findBySeverity", query = "SELECT n FROM Nodes n WHERE n.severity = :severity")})
public class Nodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodesPK nodesPK;
    @Size(max = 2147483647)
    @Column(name = "node_location")
    private String nodeLocation;
    @Size(max = 2147483647)
    @Column(name = "node_type")
    private String nodeType;
    @Size(max = 2147483647)
    @Column(name = "severity")
    private String severity;
    @JoinColumn(name = "node_domain", referencedColumnName = "domain_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Domains domains;
    @OneToMany(mappedBy = "nodes")
    private Collection<TroubleTicketNodes> troubleTicketNodesCollection;

    public Nodes() {
    }

    public Nodes(NodesPK nodesPK) {
        this.nodesPK = nodesPK;
    }

    public Nodes(String nodeName, String nodeDomain) {
        this.nodesPK = new NodesPK(nodeName, nodeDomain);
    }

    public NodesPK getNodesPK() {
        return nodesPK;
    }

    public void setNodesPK(NodesPK nodesPK) {
        this.nodesPK = nodesPK;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Domains getDomains() {
        return domains;
    }

    public void setDomains(Domains domains) {
        this.domains = domains;
    }

    @XmlTransient
    public Collection<TroubleTicketNodes> getTroubleTicketNodesCollection() {
        return troubleTicketNodesCollection;
    }

    public void setTroubleTicketNodesCollection(Collection<TroubleTicketNodes> troubleTicketNodesCollection) {
        this.troubleTicketNodesCollection = troubleTicketNodesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodesPK != null ? nodesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodes)) {
            return false;
        }
        Nodes other = (Nodes) object;
        if ((this.nodesPK == null && other.nodesPK != null) || (this.nodesPK != null && !this.nodesPK.equals(other.nodesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nodesPK.getNodeName() + " | "+nodesPK.getNodeDomain() +" | "+nodeType+" | "+nodeLocation;
    }
    
}
