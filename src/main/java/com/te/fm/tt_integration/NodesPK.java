/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eamrela
 */
@Embeddable
public class NodesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "node_name")
    private String nodeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "node_domain")
    private String nodeDomain;

    public NodesPK() {
    }

    public NodesPK(String nodeName, String nodeDomain) {
        this.nodeName = nodeName;
        this.nodeDomain = nodeDomain;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeDomain() {
        return nodeDomain;
    }

    public void setNodeDomain(String nodeDomain) {
        this.nodeDomain = nodeDomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeName != null ? nodeName.hashCode() : 0);
        hash += (nodeDomain != null ? nodeDomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodesPK)) {
            return false;
        }
        NodesPK other = (NodesPK) object;
        if ((this.nodeName == null && other.nodeName != null) || (this.nodeName != null && !this.nodeName.equals(other.nodeName))) {
            return false;
        }
        if ((this.nodeDomain == null && other.nodeDomain != null) || (this.nodeDomain != null && !this.nodeDomain.equals(other.nodeDomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.NodesPK[ nodeName=" + nodeName + ", nodeDomain=" + nodeDomain + " ]";
    }
    
}
