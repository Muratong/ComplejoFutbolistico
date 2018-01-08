/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesGestion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DelKo
 */
@Entity
@Table(name = "estado_cancha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCancha.findAll", query = "SELECT e FROM EstadoCancha e"),
    @NamedQuery(name = "EstadoCancha.findByEstCanId", query = "SELECT e FROM EstadoCancha e WHERE e.estCanId = :estCanId"),
    @NamedQuery(name = "EstadoCancha.findByEstCanEstado", query = "SELECT e FROM EstadoCancha e WHERE e.estCanEstado = :estCanEstado")})
public class EstadoCancha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_can_id")
    private Integer estCanId;
    @Size(max = 255)
    @Column(name = "est_can_estado")
    private String estCanEstado;
    @OneToMany(mappedBy = "canEstado")
    private List<Cancha> canchaList;

    public EstadoCancha() {
    }

    public EstadoCancha(Integer estCanId) {
        this.estCanId = estCanId;
    }

    public Integer getEstCanId() {
        return estCanId;
    }

    public void setEstCanId(Integer estCanId) {
        this.estCanId = estCanId;
    }

    public String getEstCanEstado() {
        return estCanEstado;
    }

    public void setEstCanEstado(String estCanEstado) {
        this.estCanEstado = estCanEstado;
    }

    @XmlTransient
    public List<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(List<Cancha> canchaList) {
        this.canchaList = canchaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estCanId != null ? estCanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCancha)) {
            return false;
        }
        EstadoCancha other = (EstadoCancha) object;
        if ((this.estCanId == null && other.estCanId != null) || (this.estCanId != null && !this.estCanId.equals(other.estCanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesGestion.EstadoCancha[ estCanId=" + estCanId + " ]";
    }
    
}
