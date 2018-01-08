/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesReservas;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DelKo
 */
@Entity
@Table(name = "estado_reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoReserva.findAll", query = "SELECT e FROM EstadoReserva e"),
    @NamedQuery(name = "EstadoReserva.findByEstResId", query = "SELECT e FROM EstadoReserva e WHERE e.estResId = :estResId"),
    @NamedQuery(name = "EstadoReserva.findByEstResEstado", query = "SELECT e FROM EstadoReserva e WHERE e.estResEstado = :estResEstado")})
public class EstadoReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_res_id")
    private Integer estResId;
    @Size(max = 255)
    @Column(name = "est_res_estado")
    private String estResEstado;
    @OneToMany(mappedBy = "resEstado")
    private List<Reserva> reservaList;

    public EstadoReserva() {
    }

    public EstadoReserva(Integer estResId) {
        this.estResId = estResId;
    }

    public Integer getEstResId() {
        return estResId;
    }

    public void setEstResId(Integer estResId) {
        this.estResId = estResId;
    }

    public String getEstResEstado() {
        return estResEstado;
    }

    public void setEstResEstado(String estResEstado) {
        this.estResEstado = estResEstado;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estResId != null ? estResId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoReserva)) {
            return false;
        }
        EstadoReserva other = (EstadoReserva) object;
        if ((this.estResId == null && other.estResId != null) || (this.estResId != null && !this.estResId.equals(other.estResId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesReservas.EstadoReserva[ estResId=" + estResId + " ]";
    }
    
}
