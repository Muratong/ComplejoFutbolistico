/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesGestion;

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
@Table(name = "servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
    @NamedQuery(name = "Servicio.findBySerId", query = "SELECT s FROM Servicio s WHERE s.serId = :serId"),
    @NamedQuery(name = "Servicio.findBySerNombre", query = "SELECT s FROM Servicio s WHERE s.serNombre = :serNombre"),
    @NamedQuery(name = "Servicio.findBySerDescripcion", query = "SELECT s FROM Servicio s WHERE s.serDescripcion = :serDescripcion"),
    @NamedQuery(name = "Servicio.findBySerPrecio", query = "SELECT s FROM Servicio s WHERE s.serPrecio = :serPrecio"),
    @NamedQuery(name = "Servicio.findBySerEstado", query = "SELECT s FROM Servicio s WHERE s.serEstado = :serEstado"),
    @NamedQuery(name = "Servicio.findBySerImg", query = "SELECT s FROM Servicio s WHERE s.serImg = :serImg")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ser_id")
    private Integer serId;
    @Size(max = 255)
    @Column(name = "ser_nombre")
    private String serNombre;
    @Size(max = 255)
    @Column(name = "ser_descripcion")
    private String serDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ser_precio")
    private Float serPrecio;
    @Size(max = 255)
    @Column(name = "ser_estado")
    private String serEstado;
    @Size(max = 255)
    @Column(name = "ser_img")
    private String serImg;
    @JoinColumn(name = "ser_complejo", referencedColumnName = "com_id")
    @ManyToOne
    private Complejo serComplejo;

    public Servicio() {
    }

    public Servicio(Integer serId) {
        this.serId = serId;
    }

    public Integer getSerId() {
        return serId;
    }

    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    public String getSerNombre() {
        return serNombre;
    }

    public void setSerNombre(String serNombre) {
        this.serNombre = serNombre;
    }

    public String getSerDescripcion() {
        return serDescripcion;
    }

    public void setSerDescripcion(String serDescripcion) {
        this.serDescripcion = serDescripcion;
    }

    public Float getSerPrecio() {
        return serPrecio;
    }

    public void setSerPrecio(Float serPrecio) {
        this.serPrecio = serPrecio;
    }

    public String getSerEstado() {
        return serEstado;
    }

    public void setSerEstado(String serEstado) {
        this.serEstado = serEstado;
    }

    public String getSerImg() {
        return serImg;
    }

    public void setSerImg(String serImg) {
        this.serImg = serImg;
    }

    public Complejo getSerComplejo() {
        return serComplejo;
    }

    public void setSerComplejo(Complejo serComplejo) {
        this.serComplejo = serComplejo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serId != null ? serId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.serId == null && other.serId != null) || (this.serId != null && !this.serId.equals(other.serId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesGestion.Servicio[ serId=" + serId + " ]";
    }
    
}
