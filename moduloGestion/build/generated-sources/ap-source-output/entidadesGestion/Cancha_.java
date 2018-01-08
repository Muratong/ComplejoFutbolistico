package entidadesGestion;

import entidadesGestion.CanchaTipo;
import entidadesGestion.Complejo;
import entidadesGestion.EstadoCancha;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:09")
@StaticMetamodel(Cancha.class)
public class Cancha_ { 

    public static volatile SingularAttribute<Cancha, CanchaTipo> canTipo;
    public static volatile SingularAttribute<Cancha, Complejo> canComplejo;
    public static volatile SingularAttribute<Cancha, Integer> canNumero;
    public static volatile SingularAttribute<Cancha, EstadoCancha> canEstado;
    public static volatile SingularAttribute<Cancha, Integer> canId;

}