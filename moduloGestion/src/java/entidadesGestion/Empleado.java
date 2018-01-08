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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByEmpId", query = "SELECT e FROM Empleado e WHERE e.empId = :empId"),
    @NamedQuery(name = "Empleado.findByEmpNombre", query = "SELECT e FROM Empleado e WHERE e.empNombre = :empNombre"),
    @NamedQuery(name = "Empleado.findByEmpApellido", query = "SELECT e FROM Empleado e WHERE e.empApellido = :empApellido"),
    @NamedQuery(name = "Empleado.findByEmpDni", query = "SELECT e FROM Empleado e WHERE e.empDni = :empDni"),
    @NamedQuery(name = "Empleado.findByEmpDomicilio", query = "SELECT e FROM Empleado e WHERE e.empDomicilio = :empDomicilio"),
    @NamedQuery(name = "Empleado.findByEmpTelefono", query = "SELECT e FROM Empleado e WHERE e.empTelefono = :empTelefono"),
    @NamedQuery(name = "Empleado.findByEmpEmail", query = "SELECT e FROM Empleado e WHERE e.empEmail = :empEmail")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id")
    private Integer empId;
    @Size(max = 255)
    @Column(name = "emp_nombre")
    private String empNombre;
    @Size(max = 255)
    @Column(name = "emp_apellido")
    private String empApellido;
    @Size(max = 255)
    @Column(name = "emp_dni")
    private String empDni;
    @Size(max = 255)
    @Column(name = "emp_domicilio")
    private String empDomicilio;
    @Size(max = 255)
    @Column(name = "emp_telefono")
    private String empTelefono;
    @Column(name = "emp_email")
    private String empEmail;
    @OneToMany(mappedBy = "comEmIdEmp")
    private List<ComplejoEmpleado> complejoEmpleadoList;

    public Empleado() {
    }

    public Empleado(Integer empId) {
        this.empId = empId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpApellido() {
        return empApellido;
    }

    public void setEmpApellido(String empApellido) {
        this.empApellido = empApellido;
    }

    public String getEmpDni() {
        return empDni;
    }

    public void setEmpDni(String empDni) {
        this.empDni = empDni;
    }

    public String getEmpDomicilio() {
        return empDomicilio;
    }

    public void setEmpDomicilio(String empDomicilio) {
        this.empDomicilio = empDomicilio;
    }

    public String getEmpTelefono() {
        return empTelefono;
    }

    public void setEmpTelefono(String empTelefono) {
        this.empTelefono = empTelefono;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    @XmlTransient
    public List<ComplejoEmpleado> getComplejoEmpleadoList() {
        return complejoEmpleadoList;
    }

    public void setComplejoEmpleadoList(List<ComplejoEmpleado> complejoEmpleadoList) {
        this.complejoEmpleadoList = complejoEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesGestion.Empleado[ empId=" + empId + " ]";
    }
    
}
