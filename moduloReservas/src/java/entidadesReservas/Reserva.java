/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesReservas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DelKo
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByResId", query = "SELECT r FROM Reserva r WHERE r.resId = :resId"),
    @NamedQuery(name = "Reserva.findByResFecha", query = "SELECT r FROM Reserva r WHERE r.resFecha = :resFecha"),
    @NamedQuery(name = "Reserva.findByResHoraInicio", query = "SELECT r FROM Reserva r WHERE r.resHoraInicio = :resHoraInicio"),
    @NamedQuery(name = "Reserva.findByResHoraFin", query = "SELECT r FROM Reserva r WHERE r.resHoraFin = :resHoraFin")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "res_id")
    private Integer resId;
    @Size(max = 255)
    @Column(name = "res_fecha")
    private String resFecha;
    @Size(max = 255)
    @Column(name = "res_hora_inicio")
    private String resHoraInicio;
    @Size(max = 255)
    @Column(name = "res_hora_fin")
    private String resHoraFin;
    @JoinColumn(name = "res_usu", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario resUsu;
    @JoinColumn(name = "res_cancha", referencedColumnName = "can_id")
    @ManyToOne
    private Cancha resCancha;
    @JoinColumn(name = "res_estado", referencedColumnName = "est_res_id")
    @ManyToOne
    private EstadoReserva resEstado;

    public Reserva() {
    }

    public Reserva(Integer resId) {
        this.resId = resId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResFecha() {
        return resFecha;
    }

    public void setResFecha(String resFecha) {
        this.resFecha = resFecha;
    }

    public String getResHoraInicio() {
        return resHoraInicio;
    }

    public void setResHoraInicio(String resHoraInicio) {
        this.resHoraInicio = resHoraInicio;
    }

    public String getResHoraFin() {
        return resHoraFin;
    }

    public void setResHoraFin(String resHoraFin) {
        this.resHoraFin = resHoraFin;
    }

    public Usuario getResUsu() {
        return resUsu;
    }

    public void setResUsu(Usuario resUsu) {
        this.resUsu = resUsu;
    }

    public Cancha getResCancha() {
        return resCancha;
    }

    public void setResCancha(Cancha resCancha) {
        this.resCancha = resCancha;
    }

    public EstadoReserva getResEstado() {
        return resEstado;
    }

    public void setResEstado(EstadoReserva resEstado) {
        this.resEstado = resEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesReservas.Reserva[ resId=" + resId + " ]";
    }
    
}
