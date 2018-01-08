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
@Table(name = "cancha_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanchaTipo.findAll", query = "SELECT c FROM CanchaTipo c"),
    @NamedQuery(name = "CanchaTipo.findByCanTipId", query = "SELECT c FROM CanchaTipo c WHERE c.canTipId = :canTipId"),
    @NamedQuery(name = "CanchaTipo.findByCanTipDescripcion", query = "SELECT c FROM CanchaTipo c WHERE c.canTipDescripcion = :canTipDescripcion"),
    @NamedQuery(name = "CanchaTipo.findByCanTipCapacidad", query = "SELECT c FROM CanchaTipo c WHERE c.canTipCapacidad = :canTipCapacidad"),
    @NamedQuery(name = "CanchaTipo.findByCantipprecioDia", query = "SELECT c FROM CanchaTipo c WHERE c.cantipprecioDia = :cantipprecioDia"),
    @NamedQuery(name = "CanchaTipo.findByCantipprecioNoche", query = "SELECT c FROM CanchaTipo c WHERE c.cantipprecioNoche = :cantipprecioNoche")})
public class CanchaTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "can_tip_id")
    private Integer canTipId;
    @Size(max = 255)
    @Column(name = "can_tip_descripcion")
    private String canTipDescripcion;
    @Size(max = 255)
    @Column(name = "can_tip_capacidad")
    private String canTipCapacidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "can_tip_precioDia")
    private Float cantipprecioDia;
    @Column(name = "can_tip_precioNoche")
    private Float cantipprecioNoche;
    @OneToMany(mappedBy = "canTipo")
    private List<Cancha> canchaList;

    public CanchaTipo() {
    }

    public CanchaTipo(Integer canTipId) {
        this.canTipId = canTipId;
    }

    public Integer getCanTipId() {
        return canTipId;
    }

    public void setCanTipId(Integer canTipId) {
        this.canTipId = canTipId;
    }

    public String getCanTipDescripcion() {
        return canTipDescripcion;
    }

    public void setCanTipDescripcion(String canTipDescripcion) {
        this.canTipDescripcion = canTipDescripcion;
    }

    public String getCanTipCapacidad() {
        return canTipCapacidad;
    }

    public void setCanTipCapacidad(String canTipCapacidad) {
        this.canTipCapacidad = canTipCapacidad;
    }

    public Float getCantipprecioDia() {
        return cantipprecioDia;
    }

    public void setCantipprecioDia(Float cantipprecioDia) {
        this.cantipprecioDia = cantipprecioDia;
    }

    public Float getCantipprecioNoche() {
        return cantipprecioNoche;
    }

    public void setCantipprecioNoche(Float cantipprecioNoche) {
        this.cantipprecioNoche = cantipprecioNoche;
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
        hash += (canTipId != null ? canTipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CanchaTipo)) {
            return false;
        }
        CanchaTipo other = (CanchaTipo) object;
        if ((this.canTipId == null && other.canTipId != null) || (this.canTipId != null && !this.canTipId.equals(other.canTipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesReservas.CanchaTipo[ canTipId=" + canTipId + " ]";
    }
    
}
