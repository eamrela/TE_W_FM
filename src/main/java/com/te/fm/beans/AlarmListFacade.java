/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.beans;

import com.te.fm.entities.AlarmList;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author eamrela
 */
@Stateless
public class AlarmListFacade extends AbstractFacade<AlarmList> {

    @PersistenceContext(unitName = "com.vodafone_TE_W_FM_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlarmListFacade() {
        super(AlarmList.class);
    }

    public List<AlarmList> findViewAlarms(String condition) {
        return em.createNativeQuery("select * from alarm_list  "+condition, AlarmList.class)
                .getResultList();
    }

    public List<AlarmList> find(String condition,Integer firsRow, Integer pageSize) {
        Query query = em.createNativeQuery("select * From alarm_list e "+condition, AlarmList.class);
        query.setFirstResult(firsRow);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int  getTotalRows(String filterCondition) {
        Query query = em.createNativeQuery("Select count(record_id) From alarm_list e "+filterCondition);
      return ((Long)query.getSingleResult()).intValue();
    }
   
    public int  getSeverityCount(String filterCondition,String severity) {
        Query query = em.createNativeQuery("Select count(record_id) From alarm_list e "+
                (filterCondition.length()>4?filterCondition+" and (alarm_severity = '"+severity+"')":"where alarm_severity = '"+severity+"'"));
      return ((Long)query.getSingleResult()).intValue();
    }

    public List<AlarmList> findNewAlarms(String condition,Date date) {
         List<AlarmList> res =  em.createNativeQuery("select * from alarm_list  "+
                 (condition.length()>4?condition+" and cease_time is null and logging_time > '"+sdf.format(date)+"' "
                         :" where cease_time is null and logging_time > '"+sdf.format(date)+"' "), AlarmList.class)
                .getResultList();
         return res;
    }
    
    
    public List<AlarmList> findCeaseAlarms(String condition,Date date) {
        DateTime d = new DateTime(date);
        d = d.minusHours(12);
         List<AlarmList> res =  em.createNativeQuery("select * from alarm_list  "+
                 (condition.length()>4?condition+" and cease_time > '"+sdf.format(d.toDate())+"' "
                         :" where cease_time > '"+sdf.format(d.toDate())+"' "), AlarmList.class)
                .getResultList();
         return res;
    }
    
    
    
}
