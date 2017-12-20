/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;


import com.te.fm.beans.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eamrela
 */
@Stateless
public class DomainsTTFacade extends AbstractFacade<com.te.fm.tt_integration.Domains> {

    @PersistenceContext(unitName = "TE_TT")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomainsTTFacade() {
        super(com.te.fm.tt_integration.Domains.class);
    }
    
}
