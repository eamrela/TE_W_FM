/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.beans;


import com.te.fm.entities.AlarmLog;
import java.text.SimpleDateFormat;
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
public class AlarmLogFacade extends AbstractFacade<AlarmLog> {

    @PersistenceContext(unitName = "com.vodafone_TE_W_FM_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlarmLogFacade() {
        super(AlarmLog.class);
    }
    
    public List<AlarmLog> find(String condition,Integer firsRow, Integer pageSize) {
        Query query = em.createNativeQuery("select * From alarm_log e "+condition, AlarmLog.class);
        query.setFirstResult(firsRow);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int  getTotalRows(String filterCondition) {
        Query query = em.createNativeQuery("Select count(record_id) From alarm_log e "+filterCondition);
      return ((Long)query.getSingleResult()).intValue();
    }

    public List<AlarmLog> findNewAlarms(String condition,Date date) {
         List<AlarmLog> res =  em.createNativeQuery("select * from alarm_log  "+
                 (condition.length()>4?condition+" and cease_time is not null and logging_time > '"+sdf.format(date)+"' "
                         :" where cease_time is not null and logging_time > '"+sdf.format(date)+"' "), AlarmLog.class)
                .getResultList();
         return res;
    }
    
    
    public List<AlarmLog> findCeaseAlarms(String condition,Date date) {
        DateTime d = new DateTime(date);
        d = d.minusHours(12);
         List<AlarmLog> res =  em.createNativeQuery("select * from alarm_log  "+
                 (condition.length()>4?condition+" and cease_time > '"+sdf.format(d.toDate())+"' "
                         :" where cease_time > '"+sdf.format(d.toDate())+"' "), AlarmLog.class)
                .getResultList();
         return res;
    }
    
    
}
