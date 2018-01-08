package entidadesReservas;

import entidadesReservas.CanchaTipo;
import entidadesReservas.Complejo;
import entidadesReservas.EstadoCancha;
import entidadesReservas.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:11")
@StaticMetamodel(Cancha.class)
public class Cancha_ { 

    public static volatile SingularAttribute<Cancha, CanchaTipo> canTipo;
    public static volatile SingularAttribute<Cancha, Complejo> canComplejo;
    public static volatile ListAttribute<Cancha, Reserva> reservaList;
    public static volatile SingularAttribute<Cancha, Integer> canNumero;
    public static volatile SingularAttribute<Cancha, EstadoCancha> canEstado;
    public static volatile SingularAttribute<Cancha, Integer> canId;

}