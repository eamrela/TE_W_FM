package com.te.fm.tt_integration;



import com.te.fm.controllers.util.JsfUtil;
import com.te.fm.controllers.util.JsfUtil.PersistAction;
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

@Named("assignmentGroupsController")
@SessionScoped
public class AssignmentGroupsController implements Serializable {

    @EJB
    private AssignmentGroupsFacade ejbFacade;
    private List<AssignmentGroups> items = null;
    private AssignmentGroups selected;
    
    private Users searchedUser;

    public Users getSearchedUser() {
        return searchedUser;
    }

    public void setSearchedUser(Users searchedUser) {
        this.searchedUser = searchedUser;
    }
    
    
    

    public AssignmentGroupsController() {
    }

    public AssignmentGroups getSelected() {
        return selected;
    }

    public void setSelected(AssignmentGroups selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AssignmentGroupsFacade getFacade() {
        return ejbFacade;
    }

    public AssignmentGroups prepareCreate() {
        selected = new AssignmentGroups();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AssignmentGroupsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AssignmentGroupsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AssignmentGroupsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AssignmentGroups> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AssignmentGroups getAssignmentGroups(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<AssignmentGroups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AssignmentGroups> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AssignmentGroups.class)
    public static class AssignmentGroupsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AssignmentGroupsController controller = (AssignmentGroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "assignmentGroupsController");
            return controller.getAssignmentGroups(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AssignmentGroups) {
                AssignmentGroups o = (AssignmentGroups) object;
                return getStringKey(o.getGroupName());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AssignmentGroups.class.getName()});
                return null;
            }
        }

    }

    public void onUserAdd(){
        if(selected!=null && searchedUser!=null){
            if(selected.getUsersCollection()!=null){
                selected.getUsersCollection().add(searchedUser);
            }else{
                selected.setUsersCollection(new ArrayList<Users>());
                selected.getUsersCollection().add(searchedUser);
            }
            
            update();
            searchedUser = null;
        }
    }
    
    public void removeUser(){
        if(selected!=null){
            selected.getUsersCollection().remove(searchedUser);
            update();
            searchedUser = null;
        }
    }
}
