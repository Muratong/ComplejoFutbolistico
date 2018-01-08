/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.ComplejoEmpleado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DelKo
 */
@Local
public interface ComplejoEmpleadoFacadeLocal {

    void create(ComplejoEmpleado complejoEmpleado);

    void edit(ComplejoEmpleado complejoEmpleado);

    void remove(ComplejoEmpleado complejoEmpleado);

    ComplejoEmpleado find(Object id);

    List<ComplejoEmpleado> findAll();

    List<ComplejoEmpleado> findRange(int[] range);

    int count();
    
}
