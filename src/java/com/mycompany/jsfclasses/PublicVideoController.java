package com.mycompany.jsfclasses;

import com.mycompany.entityclasses.PublicVideo;
import com.mycompany.jsfclasses.util.JsfUtil;
import com.mycompany.jsfclasses.util.JsfUtil.PersistAction;
import com.mycompany.sessionbeans.PublicVideoFacade;
import java.io.IOException;

import java.io.Serializable;
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
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jinwoo
 */
@Named("publicVideoController")
@SessionScoped
public class PublicVideoController implements Serializable {
    
    @EJB
    private com.mycompany.sessionbeans.PublicVideoFacade publicVideoFacade;
    
    private List<PublicVideo> items = null;
    
    private String searchString;
    
    private String searchLabel;
    
    private List<PublicVideo> searchedVideos = null;
    
    private PublicVideo selected;

    /*
    -----------------------------------------------------
    This is the constructor method invoked to instantiate
    an object from the CompanyController class
    -----------------------------------------------------
     */
    public PublicVideoController() {
    }

    /*
    -------------------------
    Getter and Setter Methods
    -------------------------
     */
    public PublicVideo getSelected() {
        return selected;
    }

    public void setSelected(PublicVideo selected) {
        this.selected = selected;
    }

    private PublicVideoFacade getPublicVideoFacade() {
        return publicVideoFacade;
    }
    /*
    -----------------------
    Embeddable Primary Keys
    -----------------------
     */
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    /*
    ----------------
    Instance Methods
    ----------------
     */
    public PublicVideo prepareCreate() {
        selected = new PublicVideo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PublicVideoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PublicVideoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PublicVideoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PublicVideo> getItems() {
        if (items == null) {
            items = getPublicVideoFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getPublicVideoFacade().edit(selected);
                } else {
                    getPublicVideoFacade().remove(selected);
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

    public PublicVideo getPublicVideo(java.lang.Integer id) {
        return getPublicVideoFacade().find(id);
    }

    public List<PublicVideo> getItemsAvailableSelectMany() {
        return getPublicVideoFacade().findAll();
    }

    public List<PublicVideo> getItemsAvailableSelectOne() {
        return getPublicVideoFacade().findAll();
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchLabel() {
        return searchLabel;
    }

    public void setSearchLabel(String searchLabel) {
        this.searchLabel = searchLabel;
    }

    public void setSearchedVideos(List<PublicVideo> searchedVideos) {
        this.searchedVideos = searchedVideos;
    }
    /*
    Return the list of object references of all those companies where 
    the search string 'searchString' entered by the user is contained in the searchField.
     */
    public List<PublicVideo> getSearchedVideos() {
        switch (searchLabel) {
            case "title":
                // Return the list of object references of all those companies where 
                // company name contains the search string 'searchString' entered by the user.
                searchedVideos = getPublicVideoFacade().titleQuery(searchString);
                break;
            case "description":
                // Return the list of object references of all those companies where 
                // city name contains the search string 'searchString' entered by the user.
                searchedVideos = getPublicVideoFacade().descriptionQuery(searchString);
                break;
            case "category":
                // Return the list of object references of all those companies where 
                // State name contains the search string 'searchString' entered by the user.
                searchedVideos = getPublicVideoFacade().categoryQuery(searchString);
                break;
            default:
                // Return the list of object references of all those companies where company name, city name, 
                // state name, or country name contains the search string 'searchString' entered by the user.
                searchedVideos = getPublicVideoFacade().allQuery(searchString);
                break;
        }
        return searchedVideos;
    }
    
    /**
     * @SessionScoped enables to preserve the values of the instance variables for the SearchResults.xhtml page to access.
     *
     * @param actionEvent refers to clicking the Submit button
     * @throws IOException if the page to be redirected to cannot be found
     */
    public void search(ActionEvent actionEvent) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("SearchResults.xhtml");
    }

    @FacesConverter(forClass = PublicVideo.class)
    public static class PublicVideoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PublicVideoController controller = (PublicVideoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "publicVideoController");
            return controller.getPublicVideo(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PublicVideo) {
                PublicVideo o = (PublicVideo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PublicVideo.class.getName()});
                return null;
            }
        }

    }

}
