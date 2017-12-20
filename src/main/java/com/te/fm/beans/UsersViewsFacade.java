/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.beans;

import com.te.fm.entities.UsersViews;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eamrela
 */
@Stateless
public class UsersViewsFacade extends AbstractFacade<UsersViews> {

    @PersistenceContext(unitName = "com.vodafone_TE_W_FM_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersViewsFacade() {
        super(UsersViews.class);
    }

    public List<UsersViews> findItemByUser(String userName) {
        return em.createNativeQuery("select * from users_views where user_id='"+userName+"' ", 
                UsersViews.class).getResultList();
    }
    
}
