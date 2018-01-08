package entidadesGestion;

import entidadesGestion.Complejo;
import entidadesGestion.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:09")
@StaticMetamodel(ComplejoEmpleado.class)
public class ComplejoEmpleado_ { 

    public static volatile SingularAttribute<ComplejoEmpleado, Empleado> comEmIdEmp;
    public static volatile SingularAttribute<ComplejoEmpleado, Integer> comEmpId;
    public static volatile SingularAttribute<ComplejoEmpleado, String> comEmpFecIngreso;
    public static volatile SingularAttribute<ComplejoEmpleado, String> comEmpFecEgreso;
    public static volatile SingularAttribute<ComplejoEmpleado, Complejo> comEmpIdCom;

}