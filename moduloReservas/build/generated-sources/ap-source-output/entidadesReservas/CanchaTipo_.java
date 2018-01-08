package entidadesReservas;

import entidadesReservas.Cancha;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:11")
@StaticMetamodel(CanchaTipo.class)
public class CanchaTipo_ { 

    public static volatile SingularAttribute<CanchaTipo, Integer> canTipId;
    public static volatile SingularAttribute<CanchaTipo, String> canTipDescripcion;
    public static volatile ListAttribute<CanchaTipo, Cancha> canchaList;
    public static volatile SingularAttribute<CanchaTipo, String> canTipCapacidad;
    public static volatile SingularAttribute<CanchaTipo, Float> cantipprecioDia;
    public static volatile SingularAttribute<CanchaTipo, Float> cantipprecioNoche;

}