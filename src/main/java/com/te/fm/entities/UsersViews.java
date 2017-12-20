/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "users_views")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersViews.findAll", query = "SELECT u FROM UsersViews u")
    , @NamedQuery(name = "UsersViews.findById", query = "SELECT u FROM UsersViews u WHERE u.id = :id")
    , @NamedQuery(name = "UsersViews.findByViewName", query = "SELECT u FROM UsersViews u WHERE u.viewName = :viewName")
    , @NamedQuery(name = "UsersViews.findByCondition", query = "SELECT u FROM UsersViews u WHERE u.condition = :condition")})
public class UsersViews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "view_name")
    private String viewName;
    @Size(max = 2147483647)
    @Column(name = "condition")
    private String condition;
    @JoinColumn(name = "user_id", referencedColumnName = "user_name")
    @ManyToOne
    private Users userId;

    public UsersViews() {
    }

    public UsersViews(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersViews)) {
            return false;
        }
        UsersViews other = (UsersViews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.fm.entities.UsersViews[ id=" + id + " ]";
    }
    
}
