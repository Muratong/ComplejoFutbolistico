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
@Table(name = "complejo_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplejoEmpleado.findAll", query = "SELECT c FROM ComplejoEmpleado c"),
    @NamedQuery(name = "ComplejoEmpleado.findByComEmpId", query = "SELECT c FROM ComplejoEmpleado c WHERE c.comEmpId = :comEmpId"),
    @NamedQuery(name = "ComplejoEmpleado.findByComEmpFecIngreso", query = "SELECT c FROM ComplejoEmpleado c WHERE c.comEmpFecIngreso = :comEmpFecIngreso"),
    @NamedQuery(name = "ComplejoEmpleado.findByComEmpFecEgreso", query = "SELECT c FROM ComplejoEmpleado c WHERE c.comEmpFecEgreso = :comEmpFecEgreso")})
public class ComplejoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_emp_id")
    private Integer comEmpId;
    @Size(max = 255)
    @Column(name = "com_emp_fec_ingreso")
    private String comEmpFecIngreso;
    @Size(max = 255)
    @Column(name = "com_emp_fec_egreso")
    private String comEmpFecEgreso;
    @JoinColumn(name = "com_emp_id_com", referencedColumnName = "com_id")
    @ManyToOne
    private Complejo comEmpIdCom;
    @JoinColumn(name = "com_em_id_emp", referencedColumnName = "emp_id")
    @ManyToOne
    private Empleado comEmIdEmp;

    public ComplejoEmpleado() {
    }

    public ComplejoEmpleado(Integer comEmpId) {
        this.comEmpId = comEmpId;
    }

    public Integer getComEmpId() {
        return comEmpId;
    }

    public void setComEmpId(Integer comEmpId) {
        this.comEmpId = comEmpId;
    }

    public String getComEmpFecIngreso() {
        return comEmpFecIngreso;
    }

    public void setComEmpFecIngreso(String comEmpFecIngreso) {
        this.comEmpFecIngreso = comEmpFecIngreso;
    }

    public String getComEmpFecEgreso() {
        return comEmpFecEgreso;
    }

    public void setComEmpFecEgreso(String comEmpFecEgreso) {
        this.comEmpFecEgreso = comEmpFecEgreso;
    }

    public Complejo getComEmpIdCom() {
        return comEmpIdCom;
    }

    public void setComEmpIdCom(Complejo comEmpIdCom) {
        this.comEmpIdCom = comEmpIdCom;
    }

    public Empleado getComEmIdEmp() {
        return comEmIdEmp;
    }

    public void setComEmIdEmp(Empleado comEmIdEmp) {
        this.comEmIdEmp = comEmIdEmp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comEmpId != null ? comEmpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplejoEmpleado)) {
            return false;
        }
        ComplejoEmpleado other = (ComplejoEmpleado) object;
        if ((this.comEmpId == null && other.comEmpId != null) || (this.comEmpId != null && !this.comEmpId.equals(other.comEmpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesGestion.ComplejoEmpleado[ comEmpId=" + comEmpId + " ]";
    }
    
}
