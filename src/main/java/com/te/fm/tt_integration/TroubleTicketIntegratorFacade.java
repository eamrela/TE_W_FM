/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eamrela
 */
@Stateless
public class TroubleTicketIntegratorFacade {
    
    @PersistenceContext(unitName = "TE_TT")
    private EntityManager em;

    public List<AssignmentGroups> findAssignmentGroups() {
        return em.createNativeQuery("select * from assignment_groups", AssignmentGroups.class).getResultList();
    }

    
    public TroubleTicketStatus getTroubleTicketStatus(String status) {
        return em.find(TroubleTicketStatus.class, status);
    }

    public Users getAutomaticUser() {
        return em.find(Users.class, "Automatic TT");
    }

    public AssignmentGroups getAutomaticGroup() {
        return em.find(AssignmentGroups.class, "Automatic TT");
    }

    public TroubleTickets merge(TroubleTickets selected) {
       return em.merge(selected);
    }

    public  TroubleTicketInvolvment mergeInvolvment(TroubleTicketInvolvment selectedInvolvment) {
        return em.merge(selectedInvolvment);
    }

    public TroubleTicketsAlarms mergeRelatedAlarm(TroubleTicketsAlarms relatedAlarm) {
        return em.merge(relatedAlarm);
    }

    public List<Domains> findDomainsItems() {
        return em.createNativeQuery("select * from domains ", Domains.class).getResultList();
    }
}
