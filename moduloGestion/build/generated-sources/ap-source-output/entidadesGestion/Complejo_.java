package entidadesGestion;

import entidadesGestion.Cancha;
import entidadesGestion.ComplejoEmpleado;
import entidadesGestion.Servicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:09")
@StaticMetamodel(Complejo.class)
public class Complejo_ { 

    public static volatile SingularAttribute<Complejo, String> comNombre;
    public static volatile SingularAttribute<Complejo, String> comDireccion;
    public static volatile ListAttribute<Complejo, ComplejoEmpleado> complejoEmpleadoList;
    public static volatile ListAttribute<Complejo, Cancha> canchaList;
    public static volatile SingularAttribute<Complejo, String> comTelefono;
    public static volatile SingularAttribute<Complejo, Integer> comId;
    public static volatile SingularAttribute<Complejo, String> comImg;
    public static volatile ListAttribute<Complejo, Servicio> servicioList;
    public static volatile SingularAttribute<Complejo, Integer> comEstado;

}