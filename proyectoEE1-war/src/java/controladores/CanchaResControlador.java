/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidadesReservas.Cancha;
import entidadesGestion.Complejo;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author DelKo
 */
@Named(value = "canchaResControlador")
@SessionScoped
public class CanchaResControlador implements Serializable {

    @EJB
    private sesionBeansReservas.CanchaFacadeLocal canchaFacade;
    private List<Cancha> items = null;
    private Cancha selected;
    private List<Cancha> canchasPorComplejo = null;
    private Complejo complejo;

    public sesionBeansReservas.CanchaFacadeLocal getCanchaFacade() {
        return canchaFacade;
    }

    public void setCanchaFacade(sesionBeansReservas.CanchaFacadeLocal canchaFacade) {
        this.canchaFacade = canchaFacade;
    }

    public Complejo getComplejo() {
        return complejo;
    }

    public List<Cancha> getCanchasPorComplejo() {
        return canchasPorComplejo;
    }

    public void setCanchasPorComplejo(List<Cancha> canchasPorComplejo) {
        this.canchasPorComplejo = canchasPorComplejo;
    }

    public void setComplejo(Complejo complejo) {
        this.complejo = complejo;
    }


    public CanchaResControlador() {
        this.selected = new Cancha();
    }

    public Cancha getSelected() {
        return selected;
    }

    public void setSelected(Cancha selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Cancha prepareCreate() {
        selected = new Cancha();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CanchaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CanchaUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CanchaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cancha> getItems() {
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

    public Cancha getCancha(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Cancha> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cancha> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    private sesionBeansReservas.CanchaFacadeLocal getFacade() {
        return canchaFacade;
    }

    @FacesConverter(forClass = Cancha.class)
    public static class CanchaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CanchaResControlador controller = (CanchaResControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "canchaControlador");
            return controller.getCancha(getKey(value));
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
            if (object instanceof Cancha) {
                Cancha o = (Cancha) object;
                return getStringKey(o.getCanId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cancha.class.getName()});
                return null;
            }
        }
    }

    public void recuperarCancha(AjaxBehaviorEvent evento) {
          try {
              setSelected(getFacade().find(getSelected().getCanId()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
