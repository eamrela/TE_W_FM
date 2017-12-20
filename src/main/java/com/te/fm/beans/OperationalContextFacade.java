/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.beans;

import com.te.fm.entities.OperationalContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eamrela
 */
@Stateless
public class OperationalContextFacade extends AbstractFacade<OperationalContext> {

    @PersistenceContext(unitName = "com.vodafone_TE_W_FM_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationalContextFacade() {
        super(OperationalContext.class);
    }
    
}
