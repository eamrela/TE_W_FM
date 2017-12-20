package com.te.fm.controllers;

import com.te.fm.entities.AlarmLog;
import com.te.fm.controllers.util.JsfUtil;
import com.te.fm.controllers.util.JsfUtil.PersistAction;
import com.te.fm.beans.AlarmLogFacade;
import com.te.fm.custom.LazyAlarmListModel;
import com.te.fm.custom.LazyAlarmLogModel;
import com.te.fm.entities.AlarmList;
import com.te.fm.entities.UsersViews;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;

@Named("alarmLogController")
@SessionScoped
public class AlarmLogController implements Serializable {

    @EJB
    private com.te.fm.beans.AlarmLogFacade ejbFacade;
    private List<AlarmLog> items = null;
    private AlarmLog selected;
    private String selectedTab;
    private Date lastUpdate;
    private TreeMap<String,LazyDataModel<AlarmLog>> userAlarms;
    
    @Inject
    private com.te.fm.controllers.UsersViewsController usersViewController;
    
    private List<AlarmLog> selectedItems;
    
      @PostConstruct
    public void initUserViews(){
        if(userAlarms==null){
        System.out.println("Initializing Alarms Log ");
        userAlarms = new TreeMap<>();
//        userAlarmsSelected = new TreeMap<>();
        lastUpdate = new Date();
        for (UsersViews view : usersViewController.getUserItems()) {
            if(selectedTab==null){
                selectedTab = view.getViewName();
            }
            if(!userAlarms.containsKey(view.getViewName())){
                if(!userAlarms.containsKey(view.getViewName())){
                    if(view.getCondition().length()>4){
                userAlarms.put(view.getViewName(), new LazyAlarmLogModel(getFacade(),view.getCondition()+" and (cease_time is not null) "));
                    }else{
                userAlarms.put(view.getViewName(), new LazyAlarmLogModel(getFacade()," where (cease_time is not null) "));
                    }
                }
            }
//            userAlarmsSelected.put(view.getViewName(), new ArrayList<AlarmList>());
        }
        }
    }
    
    public void polling(){
        System.out.println("Polling");
        lastUpdate = new Date();
//        selectedItemsTmp = selectedItems;
        for (int i = 0; i < usersViewController.getUserItems().size(); i++) {
            ((List<AlarmLog>)userAlarms.get(usersViewController.getUserItems().get(i).getViewName()).getWrappedData())
                    .addAll(getFacade().findCeaseAlarms(usersViewController.getUserItems().get(i).getCondition(),lastUpdate));
            
            RequestContext.getCurrentInstance().update("archivedAlarmsForm:userTabs:"+i+":alarmLog");
            
        }
    }


    public AlarmLogController() {
    }

    public AlarmLog getSelected() {
        return selected;
    }

    public void setSelected(AlarmLog selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlarmLogFacade getFacade() {
        return ejbFacade;
    }

    public AlarmLog prepareCreate() {
        selected = new AlarmLog();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("AlarmLogCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("AlarmLogUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("AlarmLogDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AlarmLog> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AlarmLog getAlarmLog(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<AlarmLog> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AlarmLog> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AlarmLog.class)
    public static class AlarmLogControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlarmLogController controller = (AlarmLogController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alarmLogController");
            return controller.getAlarmLog(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AlarmLog) {
                AlarmLog o = (AlarmLog) object;
                return getStringKey(o.getRecordId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AlarmLog.class.getName()});
                return null;
            }
        }

    }

    public void onTabChange(TabChangeEvent event) {
        selectedTab = event.getTab().getTitle();
        
    }
    
    public TreeMap<String, LazyDataModel<AlarmLog>> getUserAlarms() {
        return userAlarms;
    }

    public List<AlarmLog> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<AlarmLog> selectedItems) {
        if(selectedItems!=null){
            if(!selectedItems.isEmpty()){
        System.out.println("Selected Items: "+selectedItems);
        this.selectedItems = selectedItems;
            }
        }
    }

}
