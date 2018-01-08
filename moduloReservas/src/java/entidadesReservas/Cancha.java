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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DelKo
 */
@Entity
@Table(name = "cancha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancha.findAll", query = "SELECT c FROM Cancha c"),
    @NamedQuery(name = "Cancha.findByCanId", query = "SELECT c FROM Cancha c WHERE c.canId = :canId"),
    @NamedQuery(name = "Cancha.findByCanNumero", query = "SELECT c FROM Cancha c WHERE c.canNumero = :canNumero")})
public class Cancha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "can_id")
    private Integer canId;
    @Column(name = "can_numero")
    private Integer canNumero;
    @JoinColumn(name = "can_estado", referencedColumnName = "est_can_id")
    @ManyToOne
    private EstadoCancha canEstado;
    @JoinColumn(name = "can_tipo", referencedColumnName = "can_tip_id")
    @ManyToOne
    private CanchaTipo canTipo;
    @JoinColumn(name = "can_complejo", referencedColumnName = "com_id")
    @ManyToOne
    private Complejo canComplejo;
    @OneToMany(mappedBy = "resCancha")
    private List<Reserva> reservaList;

    public Cancha() {
    }

    public Cancha(Integer canId) {
        this.canId = canId;
    }

    public Integer getCanId() {
        return canId;
    }

    public void setCanId(Integer canId) {
        this.canId = canId;
    }

    public Integer getCanNumero() {
        return canNumero;
    }

    public void setCanNumero(Integer canNumero) {
        this.canNumero = canNumero;
    }

    public EstadoCancha getCanEstado() {
        return canEstado;
    }

    public void setCanEstado(EstadoCancha canEstado) {
        this.canEstado = canEstado;
    }

    public CanchaTipo getCanTipo() {
        return canTipo;
    }

    public void setCanTipo(CanchaTipo canTipo) {
        this.canTipo = canTipo;
    }

    public Complejo getCanComplejo() {
        return canComplejo;
    }

    public void setCanComplejo(Complejo canComplejo) {
        this.canComplejo = canComplejo;
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
        hash += (canId != null ? canId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancha)) {
            return false;
        }
        Cancha other = (Cancha) object;
        if ((this.canId == null && other.canId != null) || (this.canId != null && !this.canId.equals(other.canId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesReservas.Cancha[ canId=" + canId + " ]";
    }
    
}
