/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eamrela
 */
@Entity
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
    , @NamedQuery(name = "Categories.findByCategoryName", query = "SELECT c FROM Categories c WHERE c.categoryName = :categoryName")
    , @NamedQuery(name = "Categories.findByCategoryDescription", query = "SELECT c FROM Categories c WHERE c.categoryDescription = :categoryDescription")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "category_name")
    private String categoryName;
    @Size(max = 2147483647)
    @Column(name = "category_description")
    private String categoryDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private Collection<SubCategories> subCategoriesCollection;
    @OneToMany(mappedBy = "ttCategory")
    private Collection<TroubleTickets> troubleTicketsCollection;

    public Categories() {
    }

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @XmlTransient
    public Collection<SubCategories> getSubCategoriesCollection() {
        return subCategoriesCollection;
    }

    public void setSubCategoriesCollection(Collection<SubCategories> subCategoriesCollection) {
        this.subCategoriesCollection = subCategoriesCollection;
    }

    @XmlTransient
    public Collection<TroubleTickets> getTroubleTicketsCollection() {
        return troubleTicketsCollection;
    }

    public void setTroubleTicketsCollection(Collection<TroubleTickets> troubleTicketsCollection) {
        this.troubleTicketsCollection = troubleTicketsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryName != null ? categoryName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.categoryName == null && other.categoryName != null) || (this.categoryName != null && !this.categoryName.equals(other.categoryName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.Categories[ categoryName=" + categoryName + " ]";
    }
    
}
