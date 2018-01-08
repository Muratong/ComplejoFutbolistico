/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JCmail;
import controladores.util.JsfUtil;
import controladores.util.ReservaUtil;
import entidadesReservas.Cancha;
import entidadesReservas.EstadoReserva;
import entidadesReservas.Reserva;
import entidadesReservas.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.mail.MessagingException;
import org.primefaces.event.SelectEvent;
import sesionBeansReservas.ReservaFacadeLocal;

/**
 *
 * @author DelKo
 */
@Named(value = "reservaControlador")
@SessionScoped
public class ReservaControlador implements Serializable {

    //PARA PASAR PARAMETROS ENTRE BEANS INJECTO EL Q TIENE EL VALOR EN
    //EL BEAN DONDE LO VOY A USAR
    //  @ManagedProperty("#{usuarioControlador}")
    // private UsuarioControlador usuarioBean;
    @Inject
    private UsuarioControlador usuarioBean;
    @Inject
    private SesionControlador sesionUsuarioBean;
    @ManagedProperty("#{canchaResControlador}")
    private CanchaResControlador canchaResBean;
    // @Inject
    // private CanchaResControlador canchaResBean;
    @ManagedProperty("#{scheduleView}")
    private ScheduleView scheduleBean;
    @ManagedProperty("#{reservaUtil}")
    private ReservaUtil utilBean;
    @EJB
    private ReservaFacadeLocal reservaFacade;
    private List<Reserva> items = null;
    private List<Reserva> reservasPorUsuario = null;
    private Reserva reserva;
    private Date fechaYHoraIni;
    private Date fechayHoraFin;
    private int idReserva = 0;

    public ReservaFacadeLocal getReservaFacade() {
        return reservaFacade;
    }

    public void setReservaFacade(ReservaFacadeLocal reservaFacade) {
        this.reservaFacade = reservaFacade;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReservaFacadeLocal getFacade() {
        return reservaFacade;
    }

    public Reserva prepareCreate() {
        reserva = new Reserva();
        initializeEmbeddableKey();
        return reserva;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReservaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, "Reserva realizada exitosamente!");
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReservaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            reserva = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reserva> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (reserva != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(reserva);
                } else {
                    getFacade().remove(reserva);
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

    public Reserva getReserva(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Reserva> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reserva> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reserva.class)
    public static class ReservaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservaControlador controller = (ReservaControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservaControlador");
            return controller.getReserva(getKey(value));
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
            if (object instanceof Reserva) {
                Reserva o = (Reserva) object;
                return getStringKey(o.getResId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reserva.class.getName()});
                return null;
            }
        }
    }

    public void fechaInicialSelect(SelectEvent event) {
        fechaYHoraIni = (Date) event.getObject();
    }

    public void fechaFinalSelect(SelectEvent event) {
        fechayHoraFin = (Date) event.getObject();
    }

    private Date fechaYHoraInicio(String fechaInicio) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date miFecha = null;
        try {
            miFecha = formatoDelTexto.parse(fechaInicio);
            //System.out.println(miFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return miFecha;
    }

    private Date fechaYHoraFin(String fechaFin) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date miFecha = null;
        try {
            miFecha = formatoDelTexto.parse(fechaFin);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return miFecha;
    }

    public List<Reserva> verDisponibilidad() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void modificarReserva() {
        FacesContext context = FacesContext.getCurrentInstance();
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        if (getReserva().getResEstado().getEstResId() == 1 || getReserva().getResEstado().getEstResId() == 2) {
            Boolean tpoRestante = verificarTpoRestante(getReserva().getResFecha());
            if (tpoRestante) {
                Boolean existe = verificarReserva();
                if (!existe) {
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                    String ini = formatoDelTexto.format(fechaYHoraIni);
                    getReserva().setResFecha(ini);
                    getReserva().setResHoraInicio(ini);
                    String fin = formatoDelTexto.format(fechayHoraFin);
                    getReserva().setResHoraFin(fin);
                    getReserva().setResCancha(canchaResBean.getSelected());
                    getFacade().edit(getReserva()); // guardo los cambios en la BD
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su reserva fue modificada exitosamente");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La cancha no esta disponible para la fecha y hora seleccionadas");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Tiempo para modificar agotado");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No es posible modificar la reserva");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void cancelarReserva() {
        if (getReserva().getResEstado().getEstResId() == 1 || getReserva().getResEstado().getEstResId() == 2) {
            Boolean tpoRestante = verificarTpoRestante(getReserva().getResFecha());
            if (tpoRestante) {
                getReserva().setResEstado(new EstadoReserva(4));// LE CAMBIO EL ESTADO A CANCELADA
                getFacade().edit(getReserva()); // guardo el cambio de estado en la BD
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Su reserva fue cancelada exitosamente");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Tiempo para cancelar agotado");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else if (getReserva().getResEstado().getEstResId() == 4) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La reserva ya fue cancelada");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No es posible cancelar la reserva");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private Boolean verificarTpoRestante(String fechaReserva) {
        Boolean tpoOk = false;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date fecReserva = null;
        try {
            fecReserva = formatoDelTexto.parse(fechaReserva);
        } catch (ParseException ex) {
            System.out.println("ERROR CONVIRTIENDO LA FECHA");
        }
        GregorianCalendar c = new GregorianCalendar();
        Date fechaActual = c.getTime();
        fecReserva.setHours(fecReserva.getHours() - 4);
        if (fechaActual.before(fecReserva)) {
            tpoOk = true;
        }
        return tpoOk;
    }

    private boolean verificarUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioBean = (UsuarioControlador) context.getApplication().evaluateExpressionGet(context, "#{usuarioControlador}", UsuarioControlador.class);
        boolean admin = false;
        try {
            Usuario usuario = usuarioBean.buscarPorNomUsu(usuarioBean.getSelected().getUsuUsuario());
            if (usuario.getUsuRol().getRolId() == 2) {
                admin = true;
            }
        } catch (Exception e) {
            System.out.println("ERRORRRR EN RESERVAS POR USUARIO " + e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Usuario null");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return admin;
    }

    public void hacerReserva() {
        // PARA PASAR PARAMETRO ENTRE BEANS (AQUI RECIBE DEL CONTEXTO)
        FacesContext context = FacesContext.getCurrentInstance();
        sesionUsuarioBean = (SesionControlador) context.getApplication().evaluateExpressionGet(context, "#{sesionControlador}", SesionControlador.class);
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        Boolean existe = verificarReserva();
        if (!existe) { //si no existe una reserva para la cancha y fecha de ini seleccionada
            try {
                this.reserva = new Reserva();
                reserva.setResUsu(sesionUsuarioBean.getSelected()); // tomo el usuario logueado
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                String ini = formatoDelTexto.format(fechaYHoraIni);
                reserva.setResFecha(ini);
                reserva.setResCancha(canchaResBean.getSelected());
                reserva.setResHoraInicio(ini);
                String fin = formatoDelTexto.format(fechayHoraFin);
                reserva.setResHoraFin(fin);
                reserva.setResEstado(new EstadoReserva(1)); // estado en pendiente esperando confirmacion
                create();
                Boolean send = notificarCliente(sesionUsuarioBean.getSelected());
                if (send) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion!", "Revise su correo para confirmar la reserva"));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo", "No se realizo la reserva");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }

            } catch (NullPointerException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ups!", "Un error ocurrio mietras se hacia la reserva");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La cancha no esta disponible para la fecha y hora seleccionadas");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void hacerReservaAdmin(AjaxBehaviorEvent event) {
        // PARA PASAR PARAMETRO ENTRE BEANS (AQUI RECIBE DEL CONTEXTO)
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioBean = (UsuarioControlador) context.getApplication().evaluateExpressionGet(context, "#{usuarioControlador}", UsuarioControlador.class);
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        utilBean = (ReservaUtil) context.getApplication().evaluateExpressionGet(context, "#{reservaUtil}", ReservaUtil.class);
        Boolean existe = verificarReserva();
        if (!existe) { //si no existe una reserva para la cancha y fecha de ini seleccionada
            try {
                this.reserva = new Reserva();
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                String ini = formatoDelTexto.format(fechaYHoraIni);
                reserva.setResFecha(ini);
                reserva.setResCancha(canchaResBean.getSelected());
                reserva.setResHoraInicio(ini);
                String fin = formatoDelTexto.format(fechayHoraFin);
                reserva.setResHoraFin(fin);
                Usuario usuario = usuarioBean.buscarPorNomUsu(utilBean.getNomCliente());
                reserva.setResUsu(usuario);
                reserva.setResEstado(new EstadoReserva(2)); // estado en confirmada y cobro sena
                create();
            } catch (NullPointerException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ups!", "Un error ocurrio mietras se hacia la reserva");
                FacesContext.getCurrentInstance().addMessage(null, message);
                System.out.println("ERRORRRR EN HACER RESERVA " + e);
            }
        } else {
            // LOS MENSAJES SE MUESTRAN DESDE EL METODO ADD EVENT ADMIN PORQ ES EL QUE SE EJECUTA EN EL ACCTION LISTENER
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La cancha no esta disponible para la fecha y hora seleccionados");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public Boolean verificarReserva() {
        Boolean ok = false;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        String fechaIni = formatoDelTexto.format(fechaYHoraIni);
        FacesContext context = FacesContext.getCurrentInstance();
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        if (getFacade().verificarReservaExistente(canchaResBean.getSelected(), fechaIni)) {
            ok = true;
        }
        return ok;
    }

    public Boolean notificarCliente(Usuario usuLogueado) {
        Boolean send = false;
        FacesContext context = FacesContext.getCurrentInstance();
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        String correoUsu = usuLogueado.getUsuEmail();
        String complejo = canchaResBean.getSelected().getCanComplejo().getComNombre();
        String cancha = canchaResBean.getSelected().getCanNumero() + " - " + canchaResBean.getSelected().getCanTipo().getCanTipDescripcion();
        int idRes = getFacade().ultimaReservaUsuario(usuLogueado).getResId();
        String contenido = "Usted solicito una reserva con los siguientes detalles: \n\n"
                + " Complejo: " + complejo + "\n"
                + " Cancha: " + cancha + "\n"
                + " Fecha: " + fechaYHoraIni + "\n\n"
                + " Para confirmarla debe hacer click en el siguiente enlace: \n\n"
                + " http://localhost:8080/proyectoEE1-war/faces/reservaConfirmada.xhtml?idRes=" + idRes;

        JCmail mail = new JCmail("delcorrog@gmail.com", "escorpio2604", correoUsu, "Reserva", contenido);
        try {
            send = mail.send();
        } catch (MessagingException ex) {
        }
        return send;
    }

    public void confirmarReserva() {
        //  RECUPERO EL ID DE RESERVA DE LA PAG CONFIRMACIONRESERVA
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        idReserva = new Integer((String) params.get("idRes"));
        Reserva resConfirmada = getFacade().find(getIdReserva());
        if (resConfirmada != null) {
            //SI EL ESTADO DE LA RESERVA ES PENDIENTE (PUEDE QUE YA HAYA SIDO CONFIRMADA ANTES Y SE LA QUIERA VOLVER A CONFIRMAR CON EL MISMO CORREO)
            if (resConfirmada.getResEstado().getEstResId() == 1) {
                //CUANDO CONFIRMA LA RESERVA, CAMBIA EL ESTADO DE PENDIENTE A CONDFIRMADA y ACTUALIZO BD
                resConfirmada.setResEstado(new EstadoReserva(2));
                getFacade().edit(resConfirmada);
                FacesContext context = FacesContext.getCurrentInstance();
                scheduleBean = (ScheduleView) context.getApplication().evaluateExpressionGet(context, "#{scheduleView}", ScheduleView.class);
                scheduleBean.cargarReservasCliente();
            } else {
                try {
                    FacesContext contex = FacesContext.getCurrentInstance();
                    contex.getExternalContext().redirect("/proyectoEE1-war/faces/reservaYaConfirmada.xhtml");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "La reserva se confirmó exitosamente"));
                } catch (Exception e) {
                    System.out.println("Me voy al carajo, no funciona esta redireccion");
                }
            }
        } else {
            System.out.println("ERROR NULL");
        }
    }

    public Cancha recuperarCancha() {
        FacesContext context = FacesContext.getCurrentInstance();
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        Cancha canchaReservada = canchaResBean.getSelected();
        return canchaReservada;
    }

    public List<Reserva> reservasPorUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        sesionUsuarioBean = (SesionControlador) context.getApplication().evaluateExpressionGet(context, "#{sesionControlador}", SesionControlador.class);
        try {
            reservasPorUsuario = getFacade().buscarReservasPorUsuario(sesionUsuarioBean.getSelected());
        } catch (Exception e) {
            System.out.println("ERRORRRR EN RESERVAS POR USUARIO " + e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Usuario null");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return reservasPorUsuario;
    }

    public void recargarPag() {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/proyectoEE1-war/faces/vistas/reserva/ReservasAdmin.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "La reserva se confirmó exitosamente"));
        } catch (Exception e) {
            System.out.println("Me voy al carajo, no funciona esta redireccion");
        }
    }

    public int reservasVencidas() {
        int numResVen = 0;
        List<Reserva> listaReservas = getItems();
        //Calendar c = new GregorianCalendar(); ES LO MISMO Q LA OTRA
        Calendar c = Calendar.getInstance();
        Date fechaActual = c.getTime();
        for (int i = 0; i < listaReservas.size(); i++) {
            String resHoraFin = listaReservas.get(i).getResHoraFin();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
            Date fecFinReserva = null;
            try {
                fecFinReserva = formatoDelTexto.parse(resHoraFin);
                if (fecFinReserva.before(fechaActual) && listaReservas.get(i).getResEstado().getEstResId() != 5
                        && listaReservas.get(i).getResEstado().getEstResId() != 3) {
                    numResVen = numResVen + 1;
                }
            } catch (ParseException ex) {
                System.out.println("ERROR CONVIRTIENDO LA FECHA");
            }
        }
        return numResVen;
    }

    public void procesarReservasVencidas() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioBean = (UsuarioControlador) context.getApplication().evaluateExpressionGet(context, "#{usuarioControlador}", UsuarioControlador.class);

        int reservasVencidas = reservasVencidas();
        if (reservasVencidas > 0) {
            List<Reserva> listaReservas = getItems();
            Calendar c = Calendar.getInstance();
            Date fechaActual = c.getTime();
            boolean okProcesadas = false;
            for (int i = 0; i < listaReservas.size(); i++) {
                String resHoraFin = listaReservas.get(i).getResHoraFin();
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                Date fecFinReserva = null;
                try {
                    //TOMO LA FECHA DE FIN PARA CADA RESERVA
                    fecFinReserva = formatoDelTexto.parse(resHoraFin);
                    Reserva reservaProcesada = listaReservas.get(i);
                    if (fecFinReserva.before(fechaActual) && reservaProcesada.getResEstado().getEstResId() != 5 && reservaProcesada.getResEstado().getEstResId() != 3) {
                        //Usuario usuario = usuarioBean.buscarPorNomUsu(reservaProcesada.getResUsu().getUsuUsuario());
                        Usuario usuario = reservaProcesada.getResUsu();
                        //System.out.println("USUARIO: " + usuario.getUsuUsuario());
                        usuario.setUsuValoracion(generarPuntaje(usuario));
                        usuarioBean.getUsuarioFacade().edit(usuario); // edito la valoracion y guardo el usuario
                        reservaProcesada.setResEstado(new EstadoReserva(5)); // reserva vencida
                        getFacade().edit(reservaProcesada);
                        okProcesadas = true;
                    }
                } catch (ParseException ex) {
                    System.out.println("ERROR CONVIRTIENDO LA FECHA");
                }
            }
            if (okProcesadas) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se procesaron exitosamente las reservas vencidas");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No hay reservas para procesar");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public int generarPuntaje(Usuario usuario) {
        int puntaje = 0;
        List<Reserva> listaReservas = getFacade().buscarReservasPorUsuario(usuario);
        int tomada = 0;
        int reservasUsu = listaReservas.size();
        System.out.println("TAMAÑO LISTA: " + reservasUsu);
        for (int i = 0; i < reservasUsu; i++) {
            if (listaReservas.get(i).getResEstado().getEstResId() == 3) {
                tomada = tomada + 1;
            }
        }
        System.out.println("TOMADAS: " + tomada);
        puntaje = (int) ((tomada * 100) / reservasUsu);
        puntaje = puntaje / 10;
        System.out.println("PUNTAJE: " + puntaje);
        return puntaje;
    }

    public Date getFechaYHoraIni() {
        return fechaYHoraIni;
    }

    public void setFechaYHoraIni(Date fechaYHoraIni) {
        this.fechaYHoraIni = fechaYHoraIni;
    }

    public Date getFechayHoraFin() {
        return fechayHoraFin;
    }

    public void setFechayHoraFin(Date fechayHoraFin) {
        this.fechayHoraFin = fechayHoraFin;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Reserva> getReservasPorUsuario() {
        return reservasPorUsuario;
    }

    public void setReservasPorUsuario(List<Reserva> reservasPorUsuario) {
        this.reservasPorUsuario = reservasPorUsuario;
    }
}
