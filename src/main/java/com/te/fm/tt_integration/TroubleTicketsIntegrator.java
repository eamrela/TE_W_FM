/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.tt_integration;

import com.te.fm.entities.AlarmList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author eamrela
 */
@Named("ttIntegrator")
@SessionScoped
public class TroubleTicketsIntegrator implements Serializable{
    
    @EJB
    private TroubleTicketIntegratorFacade ttFacade;
    
    private List<Domains> domainsItems;
    
    private TroubleTickets selected;
    private TroubleTicketInvolvment selectedInvolvment;
    private List<AlarmList> selectecdAlarms;

    public TroubleTickets getSelected() {
        return selected;
    }

    public void setSelected(TroubleTickets selected) {
        this.selected = selected;
    }
    
    public void prepareCreate(){
        selected = new TroubleTickets();
        selectedInvolvment = new TroubleTicketInvolvment();
        selectecdAlarms = new ArrayList<>();
        selected.setTtCreationDate(new Date());
        selected.setTtLastModificationDate(new Date());
        selected.setTtStatus(getTtFacade().getTroubleTicketStatus("Assigned"));
        selected.setTtCreator(getTtFacade().getAutomaticUser());
        selected.setTtInitatorGroup(getTtFacade().getAutomaticGroup());
    }

    public TroubleTicketIntegratorFacade getTtFacade() {
        return ttFacade;
    }

    public Long create() {
        if(selected!=null){
        selected = getTtFacade().merge(selected);
        //<editor-fold defaultstate="collapsed" desc="Involvments">
        selectedInvolvment = new TroubleTicketInvolvment();
        selectedInvolvment.setTtId(selected);
        selectedInvolvment.setInvolvmentBy(selected.getTtCreator());
        selectedInvolvment.setInvolvmentTime(new Date());
        selectedInvolvment.setInvolvmentType("Creation");
        selectedInvolvment.setCurrentGroup(selected.getTtAssignmentGroup());
        selectedInvolvment.setPreviousGroup(selected.getTtInitatorGroup());
        if(selected.getTroubleTicketInvolvmentCollection()!=null){
            selected.getTroubleTicketInvolvmentCollection().add(getTtFacade().mergeInvolvment(selectedInvolvment));
        }else{
            selected.setTroubleTicketInvolvmentCollection(new ArrayList<TroubleTicketInvolvment>());
            selected.getTroubleTicketInvolvmentCollection().add(getTtFacade().mergeInvolvment(selectedInvolvment));
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Related Alarms">
            TroubleTicketsAlarms relatedAlarm = null;
            for (AlarmList selectecdAlarm : selectecdAlarms) {
                relatedAlarm = new TroubleTicketsAlarms();
                relatedAlarm.setTtId(selected);
                relatedAlarm.setAdditionalText(selectecdAlarm.getAdditionalText());
                relatedAlarm.setAlarmComment(selectecdAlarm.getAlarmComment());
                relatedAlarm.setAlarmDomain(selectecdAlarm.getAlarmDomain());
                relatedAlarm.setAlarmNode(selectecdAlarm.getAlarmNode());
                relatedAlarm.setAlarmOc(selectecdAlarm.getAlarmOc().getOcName());
                relatedAlarm.setAlarmSeverity(selectecdAlarm.getAlarmSeverity());
                relatedAlarm.setCeaseTime(selectecdAlarm.getCeaseTime());
                relatedAlarm.setEventTime(selectecdAlarm.getEventTime());
                relatedAlarm.setManagedObject(selectecdAlarm.getManagedObject());
                relatedAlarm.setProbableCause(selectecdAlarm.getProbableCause());
                relatedAlarm.setRecordId(selectecdAlarm.getRecordId());
                relatedAlarm.setServiceAffecting(selectecdAlarm.getServiceAffecting());
                relatedAlarm.setSpecificProblem(selectecdAlarm.getSpecificProblem());
                relatedAlarm.setAlarmId(selectecdAlarm.getAlarmId());
                getTtFacade().mergeRelatedAlarm(relatedAlarm);
                if(selected.getTroubleTicketsAlarmsCollection()!=null){
                    selected.getTroubleTicketsAlarmsCollection().add(getTtFacade().mergeRelatedAlarm(relatedAlarm));
                }else{
                    selected.setTroubleTicketsAlarmsCollection(new ArrayList<TroubleTicketsAlarms>());
                    selected.getTroubleTicketsAlarmsCollection().add(getTtFacade().mergeRelatedAlarm(relatedAlarm));
                }
            }
        //</editor-fold>
        selected = getTtFacade().merge(selected);
        return selected.getTtId();
        }
        return null;
    }

    public List<AlarmList> getSelectecdAlarms() {
        return selectecdAlarms;
    }

    public void setSelectecdAlarms(List<AlarmList> selectecdAlarms) {
        this.selectecdAlarms = selectecdAlarms;
    }

    public List<Domains> getDomainsItems() {
        if(domainsItems==null){
            domainsItems = getTtFacade().findDomainsItems();
        }
        return domainsItems;
    }
    
    
    
    
}
