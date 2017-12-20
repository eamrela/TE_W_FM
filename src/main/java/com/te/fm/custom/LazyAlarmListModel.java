/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te.fm.custom;

import com.te.fm.beans.AlarmListFacade;
import com.te.fm.controllers.AlarmListController;
import com.te.fm.entities.AlarmList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author eamrela
 */
public class LazyAlarmListModel extends LazyDataModel<AlarmList> implements SelectableDataModel<AlarmList> , Serializable{
    
    
    private static final long serialVersionUID = 4896057998576584536L;
    
    private AlarmListFacade dao;
    private Integer firsRow=1;
    private Integer pageSize=10;
    private Integer rowsCount=0;
    private Integer criticalCount=0;
    private Integer majorCount=0;
    private Integer minorCount=0;
    private List<AlarmList> data;
    private String fixedFilter;

    public LazyAlarmListModel() {
    }

    
    

    public LazyAlarmListModel(AlarmListFacade dao,String condition) {
        this.dao = dao;
        this.fixedFilter = condition;
        this.data=dao.find(condition,firsRow, pageSize);
    }
    
    
    @Override
    public Object getRowKey(AlarmList object) {
        return (object==null?1L:object.getRecordId());
    }

    @Override
    public AlarmList getRowData(String rowKey) {
        return dao.find(new Long(rowKey)); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public List<AlarmList> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        List<AlarmList> datalist=new ArrayList<>();
        
        String filter="";
        String filterCondition="";
        for (Map.Entry<String, Object> entrySet : filters.entrySet()) {
            String key = entrySet.getKey();
            key = key.replaceAll("([A-Z])", "_$1").toLowerCase();
            Object value = entrySet.getValue();   
            filterCondition+=" lower("+key+"::text) like '%"+value.toString().toLowerCase()+"%' AND ";
        }
        if(filterCondition.length()>6){
            filterCondition = filterCondition.substring(0,filterCondition.length()-4);
        }
        
        if(fixedFilter.length()>4){
            filter = fixedFilter +(filterCondition.length()>4?" and ("+filterCondition+")":"");
        }else{
            filter = (filterCondition.length()>4?" where ("+filterCondition+")":"");
        }
        data=dao.find(filter,first, pageSize);

        //filter
        for(AlarmList alarm : data) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        System.out.println("filterProperty: "+filterProperty);
                        Object filterValue = filters.get(filterProperty);
                        System.out.println("filterValue: "+filterValue);
                        String fieldValue = String.valueOf(alarm.getClass().getField(filterProperty).get(alarm));
                        System.out.println("fieldValue: "+fieldValue);
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                datalist.add(alarm);
            }
        }
        data=datalist;
        this.rowsCount=dao.getTotalRows(filter);
        
//        setRowCount(dao.getTotalRows(filterCondition));
        
        return data;
        
    }

    @Override
    public List<AlarmList> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<AlarmList> datalist=new ArrayList<>();
        String sortCriteria="";
        String filter="";
        if(sortField!=null?!sortField.isEmpty():false){
            if(sortField.contains("event")){
                sortField="event_time";
            }
            sortCriteria=" order by "+sortField+" "+(sortOrder.equals(SortOrder.ASCENDING)?" asc ":" desc ");
        }
        System.out.println("Ordering by: "+sortField+" sortOrder:"+sortOrder);
        String filterCondition="";
        for (Map.Entry<String, Object> entrySet : filters.entrySet()) {
            String key = entrySet.getKey();
            key = key.replaceAll("([A-Z])", "_$1").toLowerCase();
            Object value = entrySet.getValue();   
            filterCondition+=" lower("+key+"::text) like '%"+value.toString().toLowerCase()+"%' AND ";
        }
        if(filterCondition.length()>6){
            filterCondition = filterCondition.substring(0,filterCondition.length()-4);
        }
        if(fixedFilter.length()>4){
            filter = fixedFilter +(filterCondition.length()>4?" and ("+filterCondition+")":"");
        }else{
            filter = (filterCondition.length()>4?" where ("+filterCondition+")":"");
        }
        data=dao.find(filter+sortCriteria,first, pageSize);        
        this.rowsCount=dao.getTotalRows(filter);
        return data;
    }

    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPageSize() {
        return super.getPageSize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWrappedData(Object list) {
        super.setWrappedData(list); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getWrappedData() {
        return data; //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public int getRowIndex() {
        return super.getRowIndex(); //To change body of generated methods, choose Tools | Templates.
//        return -1;
    }

    @Override
    public AlarmList getRowData() {
//        return null;
        return super.getRowData(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return rowsCount;
    }

    @Override
    public boolean isRowAvailable() {
        return super.isRowAvailable(); //To change body of generated methods, choose Tools | Templates.
    }

    public AlarmListFacade getDao() {
        return dao;
    }

    public void setDao(AlarmListFacade dao) {
        this.dao = dao;
    }

    public List<AlarmList> getData() {
        return data;
    }

    public void setData(List<AlarmList> data) {
        this.data = data;
    }

    public Integer getFirsRow() {
        return firsRow;
    }

    public void setFirsRow(Integer firsRow) {
        this.firsRow = firsRow;
    }

    public Integer getCriticalCount() {
        return criticalCount;
    }

    public Integer getMajorCount() {
        return majorCount;
    }

    public Integer getMinorCount() {
        return minorCount;
    }
    
    
    
}
