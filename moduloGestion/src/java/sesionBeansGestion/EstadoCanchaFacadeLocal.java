/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.EstadoCancha;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DelKo
 */
@Local
public interface EstadoCanchaFacadeLocal {

    void create(EstadoCancha estadoCancha);

    void edit(EstadoCancha estadoCancha);

    void remove(EstadoCancha estadoCancha);

    EstadoCancha find(Object id);

    List<EstadoCancha> findAll();

    List<EstadoCancha> findRange(int[] range);

    int count();
    
}
