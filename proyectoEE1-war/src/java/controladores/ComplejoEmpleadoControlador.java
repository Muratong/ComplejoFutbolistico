/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidadesGestion.ComplejoEmpleado;
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
import sesionBeansGestion.ComplejoEmpleadoFacadeLocal;

/**
 *
 * @author DelKo
 */
@Named(value = "complejoEmpleadoControlador")
@SessionScoped
public class ComplejoEmpleadoControlador implements Serializable{

    
    @EJB
    private ComplejoEmpleadoFacadeLocal complejoEmpleadoFacade;
    private List<ComplejoEmpleado> items = null;
    private ComplejoEmpleado selected;
    
    public ComplejoEmpleadoControlador() {
    }
    
    public ComplejoEmpleado getSelected() {
        return selected;
    }

    public void setSelected(ComplejoEmpleado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ComplejoEmpleadoFacadeLocal getFacade() {
        return complejoEmpleadoFacade;
    }

    public ComplejoEmpleado prepareCreate() {
        selected = new ComplejoEmpleado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ComplejoEmpleadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComplejoEmpleadoUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ComplejoEmpleadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ComplejoEmpleado> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
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

    public ComplejoEmpleado getComplejoEmpleado(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ComplejoEmpleado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ComplejoEmpleado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ComplejoEmpleado.class)
    public static class ComplejoEmpleadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComplejoEmpleadoControlador controller = (ComplejoEmpleadoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "complejoEmpleadoControlador");
            return controller.getComplejoEmpleado(getKey(value));
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
            if (object instanceof ComplejoEmpleado) {
                ComplejoEmpleado o = (ComplejoEmpleado) object;
                return getStringKey(o.getComEmpId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ComplejoEmpleado.class.getName()});
                return null;
            }
        }

    }
    
}
