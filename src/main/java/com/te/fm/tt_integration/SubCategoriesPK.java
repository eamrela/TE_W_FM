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
public class SubCategoriesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sub_category_name")
    private String subCategoryName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "category_name")
    private String categoryName;

    public SubCategoriesPK() {
    }

    public SubCategoriesPK(String subCategoryName, String categoryName) {
        this.subCategoryName = subCategoryName;
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subCategoryName != null ? subCategoryName.hashCode() : 0);
        hash += (categoryName != null ? categoryName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCategoriesPK)) {
            return false;
        }
        SubCategoriesPK other = (SubCategoriesPK) object;
        if ((this.subCategoryName == null && other.subCategoryName != null) || (this.subCategoryName != null && !this.subCategoryName.equals(other.subCategoryName))) {
            return false;
        }
        if ((this.categoryName == null && other.categoryName != null) || (this.categoryName != null && !this.categoryName.equals(other.categoryName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.SubCategoriesPK[ subCategoryName=" + subCategoryName + ", categoryName=" + categoryName + " ]";
    }
    
}
