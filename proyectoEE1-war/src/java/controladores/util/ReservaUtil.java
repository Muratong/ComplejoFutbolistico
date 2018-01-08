/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.util;

import controladores.UsuarioControlador;
import entidadesReservas.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DelKo
 */
@Named(value = "reservaUtil")
@SessionScoped
public class ReservaUtil implements Serializable {

    @Inject
    private UsuarioControlador usuarioBean;
    private Date fechaSeleccionada;
    private String nomCliente;

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public void clienteSeleccionado(AjaxBehaviorEvent evento) {
        Usuario usuario = (Usuario) ((UIOutput) evento.getSource()).getValue();
        nomCliente = usuario.getUsuUsuario();
    }

    public ReservaUtil() {
    }

    public Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public Date getToday() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public void fechaSelect(SelectEvent event) {
        setFechaSeleccionada((Date) event.getObject());
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

}
