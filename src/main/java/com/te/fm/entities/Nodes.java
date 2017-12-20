/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "nodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nodes.findAll", query = "SELECT n FROM Nodes n")
    , @NamedQuery(name = "Nodes.findByNodeName", query = "SELECT n FROM Nodes n WHERE n.nodeName = :nodeName")
    , @NamedQuery(name = "Nodes.findByNodeLocation", query = "SELECT n FROM Nodes n WHERE n.nodeLocation = :nodeLocation")
    , @NamedQuery(name = "Nodes.findByNodeStatus", query = "SELECT n FROM Nodes n WHERE n.nodeStatus = :nodeStatus")
    , @NamedQuery(name = "Nodes.findByNodeUser", query = "SELECT n FROM Nodes n WHERE n.nodeUser = :nodeUser")
    , @NamedQuery(name = "Nodes.findByNodePassword", query = "SELECT n FROM Nodes n WHERE n.nodePassword = :nodePassword")
    , @NamedQuery(name = "Nodes.findByNodeIp", query = "SELECT n FROM Nodes n WHERE n.nodeIp = :nodeIp")
    , @NamedQuery(name = "Nodes.findByNodeType", query = "SELECT n FROM Nodes n WHERE n.nodeType = :nodeType")
    , @NamedQuery(name = "Nodes.findByLastCollectionTime", query = "SELECT n FROM Nodes n WHERE n.lastCollectionTime = :lastCollectionTime")})
public class Nodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "node_name")
    private String nodeName;
    @Size(max = 2147483647)
    @Column(name = "node_location")
    private String nodeLocation;
    @Column(name = "node_status")
    private Boolean nodeStatus;
    @Size(max = 2147483647)
    @Column(name = "node_user")
    private String nodeUser;
    @Size(max = 2147483647)
    @Column(name = "node_password")
    private String nodePassword;
    @Size(max = 2147483647)
    @Column(name = "node_ip")
    private String nodeIp;
    @Size(max = 2147483647)
    @Column(name = "node_type")
    private String nodeType;
    @Column(name = "last_collection_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCollectionTime;
    @JoinColumn(name = "node_domain", referencedColumnName = "domain_name")
    @ManyToOne(optional = false)
    private Domains nodeDomain;

    public Nodes() {
    }

    public Nodes(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public Boolean getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Boolean nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeUser() {
        return nodeUser;
    }

    public void setNodeUser(String nodeUser) {
        this.nodeUser = nodeUser;
    }

    public String getNodePassword() {
        return nodePassword;
    }

    public void setNodePassword(String nodePassword) {
        this.nodePassword = nodePassword;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Date getLastCollectionTime() {
        return lastCollectionTime;
    }

    public void setLastCollectionTime(Date lastCollectionTime) {
        this.lastCollectionTime = lastCollectionTime;
    }

    public Domains getNodeDomain() {
        return nodeDomain;
    }

    public void setNodeDomain(Domains nodeDomain) {
        this.nodeDomain = nodeDomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeName != null ? nodeName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodes)) {
            return false;
        }
        Nodes other = (Nodes) object;
        if ((this.nodeName == null && other.nodeName != null) || (this.nodeName != null && !this.nodeName.equals(other.nodeName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.fm.entities.Nodes[ nodeName=" + nodeName + " ]";
    }
    
}
