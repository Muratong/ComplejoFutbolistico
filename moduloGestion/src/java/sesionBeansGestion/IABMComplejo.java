
package sesionBeansGestion;

import entidadesGestion.Complejo;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IABMComplejo {
    
    void altaComplejo(Complejo complejo);

    void bajaComplejo(Complejo complejo);

    void modificacionComplejo(Complejo complejo);

    Boolean buscarComplejo(Object id);
    
    Complejo obtenerComplejo(Object id);

    List<Complejo> listarComplejos();

}







