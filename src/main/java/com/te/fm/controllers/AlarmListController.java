package com.te.fm.controllers;

import com.te.fm.entities.AlarmList;
import com.te.fm.controllers.util.JsfUtil;
import com.te.fm.controllers.util.JsfUtil.PersistAction;
import com.te.fm.beans.AlarmListFacade;
import com.te.fm.custom.LazyAlarmListModel;

import com.te.fm.entities.UsersViews;
import com.te.fm.tt_integration.TroubleTickets;
import com.te.fm.tt_integration.TroubleTicketsIntegrator;

import java.io.Serializable;
import java.math.BigInteger;
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

@Named("alarmListController")
@SessionScoped
public class AlarmListController implements Serializable {

    @EJB
    private com.te.fm.beans.AlarmListFacade ejbFacade;
    private List<AlarmList> items = null;
    private TreeMap<String,LazyDataModel<AlarmList>> userAlarms;
    private TreeMap<String,Integer[]> alarmsCount;
    private AlarmList selected;
    private Date lastUpdate;
    private String selectedTab;
    private String selectedAlarmComment;
    

    @Inject
    private com.te.fm.controllers.UsersViewsController usersViewController;
    @Inject
    private com.te.fm.controllers.UsersController usersController;
    @Inject
    private TroubleTicketsIntegrator troubleTicketsIntegrator;
    
    private List<AlarmList> selectedItems;
    private List<AlarmList> selectedItemsTmp;
    
    @PostConstruct
    public void initUserViews(){
        if(userAlarms==null){
        System.out.println("Initializing Alarms list ");
        userAlarms = new TreeMap<>();
        alarmsCount  = new TreeMap<>();
//        userAlarmsSelected = new TreeMap<>();
        lastUpdate = new Date();
        for (UsersViews view : usersViewController.getUserItems()) {
            if(selectedTab==null){
                selectedTab = view.getViewName();
            }
            if(!userAlarms.containsKey(view.getViewName())){
                if(!userAlarms.containsKey(view.getViewName())){
                    if(view.getCondition().length()>4){
                userAlarms.put(view.getViewName(), new LazyAlarmListModel(getFacade(),view.getCondition()+" and (cease_time is null) "));
                alarmsCount.put(view.getViewName(), new Integer[]{
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Critical"),
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Major"),
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Minor"),
                });
                    }else{
                userAlarms.put(view.getViewName(), new LazyAlarmListModel(getFacade()," where (cease_time is null) "));
                alarmsCount.put(view.getViewName(), new Integer[]{
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Critical"),
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Major"),
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Minor"),
                });
                    }
                }
            }   
        }
        }
    }
    
    public void polling(){
        System.out.println("Polling");
        lastUpdate = new Date();
//        selectedItemsTmp = selectedItems;
        for (int i = 0; i < usersViewController.getUserItems().size(); i++) {
            ((List<AlarmList>)userAlarms.get(usersViewController.getUserItems().get(i).getViewName()).getWrappedData())
                    .addAll(getFacade().findNewAlarms(usersViewController.getUserItems().get(i).getCondition(),lastUpdate));
            
            ((List<AlarmList>)userAlarms.get(usersViewController.getUserItems().get(i).getViewName()).getWrappedData())
                    .removeAll(getFacade().findCeaseAlarms(usersViewController.getUserItems().get(i).getCondition(),lastUpdate));
            
//            RequestContext.getCurrentInstance().update("activeAlarmsForm:userTabs:"+i+":alarmList");
        }
        updateCount();
        setSelectedItems(selectedItemsTmp);
        
    }


    public AlarmListController() {
    }

    public AlarmList getSelected() {
        return selected;
    }

    public void setSelected(AlarmList selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlarmListFacade getFacade() {
        return ejbFacade;
    }

    public AlarmList prepareCreate() {
        selected = new AlarmList();
        initializeEmbeddableKey();
        return selected;
        
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("AlarmListCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("AlarmListUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("AlarmListDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
//            selectedItems = null;
        }
    }

    public TreeMap<String, Integer[]> getAlarmsCount() {
        return alarmsCount;
    }

    public void setAlarmsCount(TreeMap<String, Integer[]> alarmsCount) {
        this.alarmsCount = alarmsCount;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    
    
    public List<AlarmList> getItems() {
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

    public AlarmList getAlarmList(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<AlarmList> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AlarmList> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void updateCount(){
         for (UsersViews view : usersViewController.getUserItems()) {
                    if(view.getCondition().length()>4){
                alarmsCount.put(view.getViewName(), new Integer[]{
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Critical"),
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Major"),
                getFacade().getSeverityCount(view.getCondition()+" and (cease_time is null) ", "Minor"),
                });
                    }else{
                alarmsCount.put(view.getViewName(), new Integer[]{
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Critical"),
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Major"),
                getFacade().getSeverityCount(view.getCondition()+" where (cease_time is null) ", "Minor"),
                });
                    }
        }
    }

    @FacesConverter(forClass = AlarmList.class)
    public static class AlarmListControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlarmListController controller = (AlarmListController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alarmListController");
            return controller.getAlarmList(getKey(value));
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
            if (object instanceof AlarmList) {
                AlarmList o = (AlarmList) object;
                return getStringKey(o.getRecordId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AlarmList.class.getName()});
                return null;
            }
        }

    }

    public TreeMap<String, LazyDataModel<AlarmList>> getUserAlarms() {
        return userAlarms;
    }

    public List<AlarmList> getSelectedItems() {
        return selectedItems;
    }

    
    
    
    public void setSelectedItems(List<AlarmList> selectedItems) {
        if(selectedItems!=null){
            if(!selectedItems.isEmpty()){
        selectedItemsTmp = this.selectedItems;
        System.out.println("Selected Items: "+selectedItems);
        this.selectedItems = selectedItems;
            }
        }
    }

    public void onTabChange(TabChangeEvent event) {
        selectedTab = event.getTab().getTitle();
        updateCount();
       
        
    }
    
    public void prepareComment(){
        selectedAlarmComment = null;
    }

    public void prepareOpenTT(){
        troubleTicketsIntegrator.prepareCreate();
        troubleTicketsIntegrator.setSelectecdAlarms(selectedItems);
    }

    public TroubleTicketsIntegrator getTroubleTicketsIntegrator() {
        return troubleTicketsIntegrator;
    }

    
    public String getSelectedAlarmComment() {
        return selectedAlarmComment;
    }

    public void setSelectedAlarmComment(String selectedAlarmComment) {
        this.selectedAlarmComment = selectedAlarmComment;
    }
    
   
    public void ackAlarms(){
        System.out.println("ACK Alarms");
        if(selectedItems!=null){
            for (AlarmList selectedItem : selectedItems) {
                selected = selectedItem;
                selected.setAcknowledgeTime(new Date());
                selected.setAcknowledged(true);
                selected.setAcknowledgedBy(usersController.getLoggedInUser());
                update();   
            }
        }
    }
    
    public void commentAlarms(){
        System.out.println("ACK Alarms");
        if(selectedItems!=null){
            for (AlarmList selectedItem : selectedItems) {
                selected = selectedItem;
                selected.setAlarmComment(selectedAlarmComment);
                selected.setCommentBy(usersController.getLoggedInUser());
                update();   
            }
        }
    }

    public void createTT(){
        if(selectedItems!=null){
//        troubleTicketsIntegrator.setSelectecdAlarms(selectedItems);
        Long tt_id = troubleTicketsIntegrator.create();
        if(tt_id!=null){
            for (AlarmList selectedItem : selectedItems) {
                selected = selectedItem;
                selected.setTtBy(usersController.getLoggedInUser());
                selected.setTtId(BigInteger.valueOf(tt_id));
                update();
            }
        }
        }
    }

    
}
