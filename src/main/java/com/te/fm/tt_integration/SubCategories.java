/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "sub_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCategories.findAll", query = "SELECT s FROM SubCategories s")
    , @NamedQuery(name = "SubCategories.findBySubCategoryName", query = "SELECT s FROM SubCategories s WHERE s.subCategoriesPK.subCategoryName = :subCategoryName")
    , @NamedQuery(name = "SubCategories.findByCategoryName", query = "SELECT s FROM SubCategories s WHERE s.subCategoriesPK.categoryName = :categoryName")
    , @NamedQuery(name = "SubCategories.findBySubCategoryDescription", query = "SELECT s FROM SubCategories s WHERE s.subCategoryDescription = :subCategoryDescription")})
public class SubCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubCategoriesPK subCategoriesPK;
    @Size(max = 2147483647)
    @Column(name = "sub_category_description")
    private String subCategoryDescription;
    @JoinColumn(name = "category_name", referencedColumnName = "category_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categories categories;

    public SubCategories() {
    }

    public SubCategories(SubCategoriesPK subCategoriesPK) {
        this.subCategoriesPK = subCategoriesPK;
    }

    public SubCategories(String subCategoryName, String categoryName) {
        this.subCategoriesPK = new SubCategoriesPK(subCategoryName, categoryName);
    }

    public SubCategoriesPK getSubCategoriesPK() {
        return subCategoriesPK;
    }

    public void setSubCategoriesPK(SubCategoriesPK subCategoriesPK) {
        this.subCategoriesPK = subCategoriesPK;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subCategoriesPK != null ? subCategoriesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCategories)) {
            return false;
        }
        SubCategories other = (SubCategories) object;
        if ((this.subCategoriesPK == null && other.subCategoriesPK != null) || (this.subCategoriesPK != null && !this.subCategoriesPK.equals(other.subCategoriesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.SubCategories[ subCategoriesPK=" + subCategoriesPK + " ]";
    }
    
}
