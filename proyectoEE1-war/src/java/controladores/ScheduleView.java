/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.ReservaUtil;
import entidadesReservas.Cancha;
import entidadesReservas.EstadoReserva;
import entidadesReservas.Reserva;
import entidadesReservas.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import sesionBeansReservas.CanchaFacadeLocal;

/**
 *
 * @author DelKo
 */
@Named(value = "scheduleView")
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    //PARA PASAR PARAMETROS ENTRE BEANS INJECTO EL Q TIENE EL VALOR EN
    //EL BEAN DONDE LO VOY A USAR
    
   // @ManagedProperty("#{reservaControlador}") ESTO FUNCIONA CUANDO LOS 2 SON SESSIONSCOPED
   // private ReservaControlador reservaBean;
    @Inject
    ReservaControlador reservaBean;
   // @ManagedProperty("#{usuarioControlador}")
    //private UsuarioControlador usuarioBean;
    @Inject
    UsuarioControlador usuarioBean;
   // @ManagedProperty("#{canchaResControlador}")
   // private CanchaResControlador canchaResBean;
    @Inject
    CanchaResControlador canchaResBean;
   // @ManagedProperty("#{reservaUtil}")
   // private ReservaUtil utilBean;
    @Inject
    ReservaUtil utilBean;
    @EJB
    private CanchaFacadeLocal canchaFacade;

    private List<Reserva> reservasPorUsuario = null;

    public List<Reserva> getReservasPorUsuario() {
        return reservasPorUsuario;
    }

    public void setReservasPorUsuario(List<Reserva> reservasPorUsuario) {
        this.reservasPorUsuario = reservasPorUsuario;
    }
    private Reserva reserva;
    private Date fecha;
    private Date fechaYHoraIni;
    private Date fechayHoraFin;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private int codigoReserva = 0;

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    //@PostConstruct //SE EJECUTA INMEDIATAMENTE AL CARGAR LA PAGINA CUANDO SE HACE EL CALLBACK AL CONTROLADOR
    public void cargarReservasCliente() {
        Cancha canchaRes = recuperarCancha();
        List<Reserva> reservasPorCancha = canchaFacade.listaReservasCancha(canchaRes);
        //si es cliente muestro el estado de la reserva
        eventModel = new DefaultScheduleModel();
        for (int i = 0; i < reservasPorCancha.size(); i++) {
            DefaultScheduleEvent evento = new DefaultScheduleEvent("Estado reserva: " + reservasPorCancha.get(i).getResEstado().getEstResEstado(),
                    fechaYHoraInicio(reservasPorCancha.get(i).getResHoraInicio()), fechaYHoraFin(reservasPorCancha.get(i).getResHoraFin()));
            evento.setEditable(false);
            eventModel.addEvent(evento);
        }
    }
  
    public void cargarReservasAdmin(AjaxBehaviorEvent event) {
        Cancha canchaRes = recuperarCancha();
        List<Reserva> reservasPorCancha = canchaFacade.listaReservasCancha(canchaRes);
        //SI ES ADMIN MUESTRO A NOMBRE DE QUIEN ESTA LA RESERVA
        eventModel = new DefaultScheduleModel();
        for (int i = 0; i < reservasPorCancha.size(); i++) {
          //  DefaultScheduleEvent evento = new DefaultScheduleEvent("Reserva de: " + reservasPorCancha.get(i).getResUsu().getUsuUsuario()
          //          + " - Estado: " + reservasPorCancha.get(i).getResEstado().getEstResEstado(),
          //          fechaYHoraInicio(reservasPorCancha.get(i).getResHoraInicio()), fechaYHoraFin(reservasPorCancha.get(i).getResHoraFin()));
           DefaultScheduleEvent evento = new DefaultScheduleEvent("" + reservasPorCancha.get(i).getResId() 
                   + " - Reserva de: " + reservasPorCancha.get(i).getResUsu().getUsuUsuario()
                   + " - Estado: " + reservasPorCancha.get(i).getResEstado().getEstResEstado(),
                   fechaYHoraInicio(reservasPorCancha.get(i).getResHoraInicio()), fechaYHoraFin(reservasPorCancha.get(i).getResHoraFin()));
           evento.setEditable(false);
           eventModel.addEvent(evento);       
        }
    }
    
        @PostConstruct
        public void cargarReservasAdmin() {
        Cancha canchaRes = recuperarCancha();
        List<Reserva> reservasPorCancha = canchaFacade.listaReservasCancha(canchaRes);
        //SI ES ADMIN MUESTRO A NOMBRE DE QUIEN ESTA LA RESERVA
        eventModel = new DefaultScheduleModel();
        for (int i = 0; i < reservasPorCancha.size(); i++) {
          //  DefaultScheduleEvent evento = new DefaultScheduleEvent("Reserva de: " + reservasPorCancha.get(i).getResUsu().getUsuUsuario()
          //          + " - Estado: " + reservasPorCancha.get(i).getResEstado().getEstResEstado(),
          //          fechaYHoraInicio(reservasPorCancha.get(i).getResHoraInicio()), fechaYHoraFin(reservasPorCancha.get(i).getResHoraFin()));
           DefaultScheduleEvent evento = new DefaultScheduleEvent("" + reservasPorCancha.get(i).getResId() 
                   + " - Reserva de: " + reservasPorCancha.get(i).getResUsu().getUsuUsuario()
                   + " - Estado: " + reservasPorCancha.get(i).getResEstado().getEstResEstado(),
                   fechaYHoraInicio(reservasPorCancha.get(i).getResHoraInicio()), fechaYHoraFin(reservasPorCancha.get(i).getResHoraFin()));
           evento.setEditable(false);
           eventModel.addEvent(evento);       
        }
    }
   
    public Date getFecha() {
        return fecha;
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

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void fechaSelect(SelectEvent event) {
        fecha = (Date) event.getObject();
    }

    public void fechaInicialSelect(SelectEvent event) {
        fechaYHoraIni = (Date) event.getObject();
    }

    public void fechaFinalSelect(SelectEvent event) {
        fechayHoraFin = (Date) event.getObject();
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
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
            //System.out.println(miFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return miFecha;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        reservaBean = (ReservaControlador) context.getApplication().evaluateExpressionGet(context, "#{reservaControlador}", ReservaControlador.class);
        if (!reservaBean.verificarReserva()) { //si no existe la reserva agrego el evento al schedule
            DefaultScheduleEvent evento = (DefaultScheduleEvent) event;
            evento.setEditable(false);
            evento.setTitle("Estado reserva: PENDIENTE");
            evento.setStartDate(reservaBean.getFechaYHoraIni());
            evento.setEndDate(reservaBean.getFechayHoraFin());
            if (event.getId() == null) {
                eventModel.addEvent(evento);
            } else {
                eventModel.updateEvent(evento);
            }
            event = new DefaultScheduleEvent();
        }
    }

    public void addEventAdmin(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioBean = (UsuarioControlador) context.getApplication().evaluateExpressionGet(context, "#{usuarioControlador}", UsuarioControlador.class);
        reservaBean = (ReservaControlador) context.getApplication().evaluateExpressionGet(context, "#{reservaControlador}", ReservaControlador.class);
        utilBean = (ReservaUtil) context.getApplication().evaluateExpressionGet(context, "#{reservaUtil}", ReservaUtil.class);
        if (!reservaBean.verificarReserva()) { //si no existe la reserva agrego el evento al schedule
            int idRes = reservaBean.getReservaFacade().ultimaReservaUsuario(usuarioBean.getSelected()).getResId();
            DefaultScheduleEvent evento = (DefaultScheduleEvent) event;
            evento.setEditable(false);
            evento.setTitle(idRes + " - Reserva de: " +utilBean.getNomCliente() + " - Estado reserva: CONFIRMADA");
            evento.setStartDate(reservaBean.getFechaYHoraIni());
            evento.setEndDate(reservaBean.getFechayHoraFin());
            if (event.getId() == null) {
                eventModel.addEvent(evento);
            } else {
                eventModel.updateEvent(evento);
            }
            event = new DefaultScheduleEvent();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Reserva exitosa");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La cancha no esta disponible para la fecha y hora seleccionados");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        setEvent((ScheduleEvent) selectEvent.getObject());
        String[] partes = getEvent().getTitle().split(" ");
        String codigo = partes[0];
        codigoReserva = Integer.parseInt(codigo);
        FacesContext context = FacesContext.getCurrentInstance();
        reservaBean = (ReservaControlador) context.getApplication().evaluateExpressionGet(context, "#{reservaControlador}", ReservaControlador.class);
        reserva = reservaBean.getReserva(codigoReserva);
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getMaxTime() {
        Integer time = 24;
        return time.toString() + ":00";
    }

    public String getMinTime() {
        Integer time = 15;
        return time.toString() + ":00";
    }

    public Date getToday() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
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
            System.out.println("ERRORRRR EN verificar usuario " + e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Usuario null");
            addMessage(message);
        }
        return admin;
    }

    public Cancha recuperarCancha() {
        FacesContext context = FacesContext.getCurrentInstance();
        canchaResBean = (CanchaResControlador) context.getApplication().evaluateExpressionGet(context, "#{canchaResControlador}", CanchaResControlador.class);
        Cancha canchaReservada = canchaResBean.getSelected();
        return canchaReservada;
    }
    
    public void tomarReserva(){
        FacesContext context = FacesContext.getCurrentInstance();
        reservaBean = (ReservaControlador) context.getApplication().evaluateExpressionGet(context, "#{reservaControlador}", ReservaControlador.class);
        reserva = reservaBean.getReserva(codigoReserva);
        if(reserva.getResEstado().getEstResId() >= 3 ){
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No es posible tomar esta reserva");
             addMessage(message);
        }else{
            reserva.setResEstado(new EstadoReserva(3));
            reservaBean.getReservaFacade().edit(reserva);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La reserva ha sido tomada");
            addMessage(message);
        }
    }

}
