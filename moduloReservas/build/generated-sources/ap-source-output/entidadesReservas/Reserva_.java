package entidadesReservas;

import entidadesReservas.Cancha;
import entidadesReservas.EstadoReserva;
import entidadesReservas.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:11")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, EstadoReserva> resEstado;
    public static volatile SingularAttribute<Reserva, Cancha> resCancha;
    public static volatile SingularAttribute<Reserva, String> resHoraInicio;
    public static volatile SingularAttribute<Reserva, Usuario> resUsu;
    public static volatile SingularAttribute<Reserva, String> resHoraFin;
    public static volatile SingularAttribute<Reserva, Integer> resId;
    public static volatile SingularAttribute<Reserva, String> resFecha;

}