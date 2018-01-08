/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidadesReservas.Reserva;
import entidadesReservas.Rol;
import entidadesReservas.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import sesionBeansReservas.UsuarioFacadeLocal;

/**
 *
 * @author DelKo
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    @Inject
    ReservaControlador reservaBean;
    @Inject
    SesionControlador sesionUsuarioBean;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private List<Usuario> items = null;
    private Usuario selected;

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    private List<Usuario> listaClientes = null;
    private List<Usuario> listaAdministradores = null;

    public List<Usuario> getListaAdministradores() {
        try {
            setListaAdministradores(getFacade().listarAdministradores());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error: " + e));
        }
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Usuario> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public List<Usuario> getListaClientes() {
        try {
            setListaClientes(getFacade().listarClientes());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error: " + e));
        }
        return listaClientes;
    }

    public void setListaClientes(List<Usuario> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public UsuarioControlador() {
        this.selected = new Usuario();
        crearCliente();
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacadeLocal getFacade() {
        return usuarioFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioControlador controller = (UsuarioControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioControlador");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getUsuId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }
    }

    public void crearCliente() {
        selected.setUsuRol(new Rol(1));
    }

    public Usuario buscarPorNomUsu(String nomUsu) {
        //setSelected(getFacade().findByNomUsu(nomUsu));
        return getFacade().findByNomUsu(nomUsu);
    }
    
    public Usuario buscarPorNomUsuParaPerfil(String nomUsu) {
        setSelected(getFacade().findByNomUsu(nomUsu));
        return getFacade().findByNomUsu(nomUsu);
    }

    public void recuperarUsuario(AjaxBehaviorEvent evento) {
        try {
            setSelected(getFacade().find(getSelected().getUsuId()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //EL SIGNIFICADO DEL PUNTAJE
    public String generarValoracion() {
        FacesContext context = FacesContext.getCurrentInstance();
        sesionUsuarioBean = (SesionControlador) context.getApplication().evaluateExpressionGet(context, "#{sesionControlador}", SesionControlador.class);
        String valoracion = null;
        int p = generarPuntaje();
        sesionUsuarioBean.getSelected().setUsuValoracion(p);
        sesionUsuarioBean.getUsuarioFacade().edit(sesionUsuarioBean.getSelected());
        int puntaje = sesionUsuarioBean.getSelected().getUsuValoracion();
        

        if (9 < puntaje && puntaje <= 10) {
            valoracion = "EXCELENTE";
        }
        if (7 < puntaje && puntaje <= 9) {
            valoracion = "BUENA";
        }
        if (5 < puntaje && puntaje <= 7) {
            valoracion = "REGULAR";
        }
        if (0 <= puntaje && puntaje <= 5) {
            valoracion = "MALA";
        }
        return valoracion;
    }

    public int generarPuntaje() {
        int puntaje = 0;
        List<Reserva> listaReservas = reservaBean.reservasPorUsuario();
        int tomada = 0;
        int reservasUsu = listaReservas.size();
        System.out.println("TAMAÑO LISTA: " + reservasUsu);
        for (int i = 0; i < reservasUsu; i++) {
            if (listaReservas.get(i).getResEstado().getEstResId() == 3) {
                tomada = tomada + 1;
            }
        }
        System.out.println("TOMADAS: " +  tomada);
        puntaje = (int) ((tomada * 100) / reservasUsu);
        puntaje = puntaje / 10;
        return puntaje;
    }
}
