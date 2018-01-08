/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.CanchaTipo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DelKo
 */
@Local
public interface CanchaTipoFacadeLocal {

    void create(CanchaTipo canchaTipo);

    void edit(CanchaTipo canchaTipo);

    void remove(CanchaTipo canchaTipo);

    CanchaTipo find(Object id);

    List<CanchaTipo> findAll();

    List<CanchaTipo> findRange(int[] range);

    int count();
    
}
