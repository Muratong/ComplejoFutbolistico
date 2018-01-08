/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.EstadoCancha;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DelKo
 */
@Stateless
public class EstadoCanchaFacade extends AbstractFacade<EstadoCancha> implements EstadoCanchaFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCanchaFacade() {
        super(EstadoCancha.class);
    }
    
}
