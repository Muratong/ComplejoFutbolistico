/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.Complejo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DelKo
 */
@Local
public interface ComplejoFacadeLocal {

    void create(Complejo complejo);

    void edit(Complejo complejo);

    void remove(Complejo complejo);

    Complejo find(Object id);

    List<Complejo> findAll();

    List<Complejo> findRange(int[] range);

    int count();
    
}
