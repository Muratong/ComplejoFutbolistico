/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.ComplejoEmpleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DelKo
 */
@Stateless
public class ComplejoEmpleadoFacade extends AbstractFacade<ComplejoEmpleado> implements ComplejoEmpleadoFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComplejoEmpleadoFacade() {
        super(ComplejoEmpleado.class);
    }
    
}
