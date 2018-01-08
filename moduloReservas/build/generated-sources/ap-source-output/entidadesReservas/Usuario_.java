package entidadesReservas;

import entidadesReservas.Reserva;
import entidadesReservas.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-04T22:27:11")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuNombre;
    public static volatile SingularAttribute<Usuario, String> usuUsuario;
    public static volatile SingularAttribute<Usuario, Integer> usuId;
    public static volatile ListAttribute<Usuario, Reserva> reservaList;
    public static volatile SingularAttribute<Usuario, Integer> usuValoracion;
    public static volatile SingularAttribute<Usuario, String> usuEmail;
    public static volatile SingularAttribute<Usuario, Rol> usuRol;
    public static volatile SingularAttribute<Usuario, String> usuApellido;
    public static volatile SingularAttribute<Usuario, String> usuEstado;
    public static volatile SingularAttribute<Usuario, String> usuFecAlta;
    public static volatile SingularAttribute<Usuario, String> usuClave;

}