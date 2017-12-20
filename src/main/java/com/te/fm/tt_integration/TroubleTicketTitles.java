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
@Table(name = "trouble_ticket_titles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TroubleTicketTitles.findAll", query = "SELECT t FROM TroubleTicketTitles t")
    , @NamedQuery(name = "TroubleTicketTitles.findById", query = "SELECT t FROM TroubleTicketTitles t WHERE t.id = :id")
    , @NamedQuery(name = "TroubleTicketTitles.findByTitleText", query = "SELECT t FROM TroubleTicketTitles t WHERE t.titleText = :titleText")})
public class TroubleTicketTitles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "title_text")
    private String titleText;

    public TroubleTicketTitles() {
    }

    public TroubleTicketTitles(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
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
        if (!(object instanceof TroubleTicketTitles)) {
            return false;
        }
        TroubleTicketTitles other = (TroubleTicketTitles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.te.tt.entities.TroubleTicketTitles[ id=" + id + " ]";
    }
    
}
