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
@Table(name = "complejo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complejo.findAll", query = "SELECT c FROM Complejo c"),
    @NamedQuery(name = "Complejo.findByComId", query = "SELECT c FROM Complejo c WHERE c.comId = :comId"),
    @NamedQuery(name = "Complejo.findByComNombre", query = "SELECT c FROM Complejo c WHERE c.comNombre = :comNombre"),
    @NamedQuery(name = "Complejo.findByComDireccion", query = "SELECT c FROM Complejo c WHERE c.comDireccion = :comDireccion"),
    @NamedQuery(name = "Complejo.findByComTelefono", query = "SELECT c FROM Complejo c WHERE c.comTelefono = :comTelefono"),
    @NamedQuery(name = "Complejo.findByComEstado", query = "SELECT c FROM Complejo c WHERE c.comEstado = :comEstado"),
    @NamedQuery(name = "Complejo.findByComImg", query = "SELECT c FROM Complejo c WHERE c.comImg = :comImg")})
public class Complejo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_id")
    private Integer comId;
    @Size(max = 255)
    @Column(name = "com_nombre")
    private String comNombre;
    @Size(max = 255)
    @Column(name = "com_direccion")
    private String comDireccion;
    @Size(max = 255)
    @Column(name = "com_telefono")
    private String comTelefono;
    @Column(name = "com_estado")
    private Integer comEstado;
    @Size(max = 255)
    @Column(name = "com_img")
    private String comImg;
    @OneToMany(mappedBy = "comEmpIdCom")
    private List<ComplejoEmpleado> complejoEmpleadoList;
    @OneToMany(mappedBy = "serComplejo")
    private List<Servicio> servicioList;
    @OneToMany(mappedBy = "canComplejo")
    private List<Cancha> canchaList;

    public Complejo() {
    }

    public Complejo(Integer comId) {
        this.comId = comId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public String getComDireccion() {
        return comDireccion;
    }

    public void setComDireccion(String comDireccion) {
        this.comDireccion = comDireccion;
    }

    public String getComTelefono() {
        return comTelefono;
    }

    public void setComTelefono(String comTelefono) {
        this.comTelefono = comTelefono;
    }

    public Integer getComEstado() {
        return comEstado;
    }

    public void setComEstado(Integer comEstado) {
        this.comEstado = comEstado;
    }

    public String getComImg() {
        return comImg;
    }

    public void setComImg(String comImg) {
        this.comImg = comImg;
    }

    @XmlTransient
    public List<ComplejoEmpleado> getComplejoEmpleadoList() {
        return complejoEmpleadoList;
    }

    public void setComplejoEmpleadoList(List<ComplejoEmpleado> complejoEmpleadoList) {
        this.complejoEmpleadoList = complejoEmpleadoList;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
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
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complejo)) {
            return false;
        }
        Complejo other = (Complejo) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesGestion.Complejo[ comId=" + comId + " ]";
    }
    
}
