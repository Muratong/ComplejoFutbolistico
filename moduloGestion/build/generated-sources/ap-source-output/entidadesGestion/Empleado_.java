package entidadesGestion;

import entidadesGestion.ComplejoEmpleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:09")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Integer> empId;
    public static volatile SingularAttribute<Empleado, String> empNombre;
    public static volatile SingularAttribute<Empleado, String> empApellido;
    public static volatile SingularAttribute<Empleado, String> empEmail;
    public static volatile ListAttribute<Empleado, ComplejoEmpleado> complejoEmpleadoList;
    public static volatile SingularAttribute<Empleado, String> empDni;
    public static volatile SingularAttribute<Empleado, String> empDomicilio;
    public static volatile SingularAttribute<Empleado, String> empTelefono;

}