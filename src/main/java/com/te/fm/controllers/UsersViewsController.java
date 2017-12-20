package com.te.fm.controllers;

import com.te.fm.entities.UsersViews;
import com.te.fm.controllers.util.JsfUtil;
import com.te.fm.controllers.util.JsfUtil.PersistAction;
import com.te.fm.beans.UsersViewsFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("usersViewsController")
@SessionScoped
public class UsersViewsController implements Serializable {

    @EJB
    private com.te.fm.beans.UsersViewsFacade ejbFacade;
    private List<UsersViews> items = null;
    private List<UsersViews> userItems = null;
    private UsersViews selected;
    private List<String> selectedSeverties;
    private List<String> selectedAck;
    private List<String> selectedAdditionalText;
    private List<String> selectedDomains;
    private List<String> selectedManagedObjects;
    private List<String> selectedNodes;
    private List<String> selectedSpecificProblems;
    private List<String> selectedAlarmComments;
    private List<String> selectedServiceAffecting;
    private List<String> selectedTT_id;
    private List<String> selectedAlarmOC;
    
    @Inject
    private com.te.fm.controllers.UsersController usersController;

    public UsersViewsController() {
    }

    public UsersViews getSelected() {
        return selected;
    }

    public void setSelected(UsersViews selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsersViewsFacade getFacade() {
        return ejbFacade;
    }

    public UsersViews prepareCreate() {
        selected = new UsersViews();
        selected.setUserId(usersController.getLoggedInUser());
        selectedSeverties = new ArrayList<>();
        selectedAck = new ArrayList<>();
        selectedAdditionalText = new ArrayList<>();
        selectedDomains = new ArrayList<>();
        selectedManagedObjects = new ArrayList<>();
        selectedNodes = new ArrayList<>();
        selectedSpecificProblems = new ArrayList<>();
        selectedAlarmComments = new ArrayList<>();
        selectedServiceAffecting = new ArrayList<>();
        selectedTT_id = new ArrayList<>();
        selectedAlarmOC = new ArrayList<>();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle3").getString("UsersViewsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            userItems = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle3").getString("UsersViewsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle3").getString("UsersViewsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            userItems = null;
        }
    }

    public List<UsersViews> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<UsersViews> getUserItems() {
        if(usersController.getLoggedInUser()!=null){
        userItems = getFacade().findItemByUser(usersController.getLoggedInUser().getUserName());
        }
        return userItems;
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsersViews getUsersViews(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<UsersViews> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsersViews> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsersViews.class)
    public static class UsersViewsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersViewsController controller = (UsersViewsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usersViewsController");
            return controller.getUsersViews(getKey(value));
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
            if (object instanceof UsersViews) {
                UsersViews o = (UsersViews) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsersViews.class.getName()});
                return null;
            }
        }

    }

    public UsersViewsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsersViewsFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<String> getSelectedSeverties() {
        return selectedSeverties;
    }

    public void setSelectedSeverties(List<String> selectedSeverties) {
        this.selectedSeverties = selectedSeverties;
    }

    public List<String> getSelectedAck() {
        return selectedAck;
    }

    public void setSelectedAck(List<String> selectedAck) {
        this.selectedAck = selectedAck;
    }

    public List<String> getSelectedAdditionalText() {
        return selectedAdditionalText;
    }

    public void setSelectedAdditionalText(List<String> selectedAdditionalText) {
        this.selectedAdditionalText = selectedAdditionalText;
    }

    public List<String> getSelectedDomains() {
        return selectedDomains;
    }

    public void setSelectedDomains(List<String> selectedDomains) {
        this.selectedDomains = selectedDomains;
    }

    public List<String> getSelectedManagedObjects() {
        return selectedManagedObjects;
    }

    public void setSelectedManagedObjects(List<String> selectedManagedObjects) {
        this.selectedManagedObjects = selectedManagedObjects;
    }

    public List<String> getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(List<String> selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public List<String> getSelectedSpecificProblems() {
        return selectedSpecificProblems;
    }

    public void setSelectedSpecificProblems(List<String> selectedSpecificProblems) {
        this.selectedSpecificProblems = selectedSpecificProblems;
    }

    public List<String> getSelectedAlarmComments() {
        return selectedAlarmComments;
    }

    public void setSelectedAlarmComments(List<String> selectedAlarmComments) {
        this.selectedAlarmComments = selectedAlarmComments;
    }

    public List<String> getSelectedServiceAffecting() {
        return selectedServiceAffecting;
    }

    public void setSelectedServiceAffecting(List<String> selectedServiceAffecting) {
        this.selectedServiceAffecting = selectedServiceAffecting;
    }

    public List<String> getSelectedTT_id() {
        return selectedTT_id;
    }

    public void setSelectedTT_id(List<String> selectedTT_id) {
        this.selectedTT_id = selectedTT_id;
    }

    public List<String> getSelectedAlarmOC() {
        return selectedAlarmOC;
    }

    public void setSelectedAlarmOC(List<String> selectedAlarmOC) {
        this.selectedAlarmOC = selectedAlarmOC;
    }

    public void createFilter(){
        if(selected!=null){
            String condition = "";
            //<editor-fold defaultstate="collapsed" desc="Condition">
            if(selectedSeverties.size()>0){
                condition += "and  (";
                for (String selectedSevertie : selectedSeverties) {
                    condition += " alarm_severity = '"+selectedSevertie+"' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            if(selectedAck.size()>0){
                condition += " and (";
                for (String ack : selectedAck) {
                    condition += " acknowledged is "+ack.equals("Yes")+" or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            if(selectedAdditionalText!=null){
            if(selectedAdditionalText.size()>0){
                condition += " and (";
                for (String addTxt : selectedAdditionalText) {
                    condition += " additional_text like '%"+addTxt+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            }
            if(selectedDomains.size()>0){
                condition += " and (";
                for (String domain : selectedDomains) {
                    condition += " alarm_domain = '"+domain+"' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            if(selectedManagedObjects!=null){
                if(selectedManagedObjects.size()>0){
                condition += " and (";
                for (String mo : selectedManagedObjects) {
                    condition += " managed_object like '%"+mo+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
                }
            }
            if(selectedNodes!=null){
                if(selectedNodes.size()>0){
                condition += " and (";
                for (String node : selectedNodes) {
                    condition += " alarm_node like '%"+node+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
                }
            }
            if(selectedSpecificProblems!=null){
                if(selectedSpecificProblems.size()>0){
                condition += " and (";
                for (String sp : selectedSpecificProblems) {
                    condition += " specific_problem like '%"+sp+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
                }
            }
            if(selectedAlarmComments!=null){
                if(selectedAlarmComments.size()>0){
                condition += " and (";
                for (String alarmComment : selectedAlarmComments) {
                    condition += " alarm_comment like '%"+alarmComment+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
                }
            }
            if(selectedServiceAffecting.size()>0){
                condition += " and (";
                for (String serviceAffecting : selectedServiceAffecting) {
                    condition += " service_affecting is "+serviceAffecting.equals("Yes")+" or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            if(selectedTT_id!=null){
                if(selectedTT_id.size()>0){
                condition += " and (";
                for (String tt : selectedTT_id) {
                    condition += " tt_id like '%"+tt+"%' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
                }
            }
            if(selectedAlarmOC.size()>0){
                condition += " and (";
                for (String oc : selectedAlarmOC) {
                    condition += " alarm_oc = '"+oc+"' or ";
                }
                condition = condition.substring(0,condition.length()-3)+") ";
            }
            if(condition.length()>4){
            condition = "where "+condition.substring(4,condition.length());
            }
            //</editor-fold>
            selected.setCondition(condition);
            create();
            
            
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/TE_W_FM/app/control_panel/view_filters.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsersViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
