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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DelKo
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuId", query = "SELECT u FROM Usuario u WHERE u.usuId = :usuId"),
    @NamedQuery(name = "Usuario.findByUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre = :usuNombre"),
    @NamedQuery(name = "Usuario.findByUsuApellido", query = "SELECT u FROM Usuario u WHERE u.usuApellido = :usuApellido"),
    @NamedQuery(name = "Usuario.findByUsuUsuario", query = "SELECT u FROM Usuario u WHERE u.usuUsuario = :usuUsuario"),
    @NamedQuery(name = "Usuario.findByUsuClave", query = "SELECT u FROM Usuario u WHERE u.usuClave = :usuClave"),
    @NamedQuery(name = "Usuario.findByUsuEstado", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado"),
    @NamedQuery(name = "Usuario.findByUsuEmail", query = "SELECT u FROM Usuario u WHERE u.usuEmail = :usuEmail"),
    @NamedQuery(name = "Usuario.findByUsuFecAlta", query = "SELECT u FROM Usuario u WHERE u.usuFecAlta = :usuFecAlta"),
    @NamedQuery(name = "Usuario.findByUsuValoracion", query = "SELECT u FROM Usuario u WHERE u.usuValoracion = :usuValoracion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Size(max = 255)
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Size(max = 255)
    @Column(name = "usu_apellido")
    private String usuApellido;
    @Size(max = 255)
    @Column(name = "usu_usuario")
    private String usuUsuario;
    @Size(max = 255)
    @Column(name = "usu_clave")
    private String usuClave;
    @Size(max = 255)
    @Column(name = "usu_estado")
    private String usuEstado;
    @Size(max = 255)
    @Column(name = "usu_email")
    private String usuEmail;
    @Size(max = 255)
    @Column(name = "usu_fec_alta")
    private String usuFecAlta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "usu_valoracion")
    private int usuValoracion;
    @JoinColumn(name = "usu_rol", referencedColumnName = "rol_id")
    @ManyToOne
    private Rol usuRol;
    @OneToMany(mappedBy = "resUsu")
    private List<Reserva> reservaList;

    public Usuario() {
    }

    public Usuario(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellido() {
        return usuApellido;
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido = usuApellido;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuFecAlta() {
        return usuFecAlta;
    }

    public void setUsuFecAlta(String usuFecAlta) {
        this.usuFecAlta = usuFecAlta;
    }

    public int getUsuValoracion() {
        return usuValoracion;
    }

    public void setUsuValoracion(int usuValoracion) {
        this.usuValoracion = usuValoracion;
    }

    public Rol getUsuRol() {
        return usuRol;
    }

    public void setUsuRol(Rol usuRol) {
        this.usuRol = usuRol;
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
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesReservas.Usuario[ usuId=" + usuId + " ]";
    }
    
}
