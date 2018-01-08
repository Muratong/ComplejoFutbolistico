/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.EstadoReserva;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DelKo
 */
@Stateless
public class EstadoReservaFacade extends AbstractFacade<EstadoReserva> implements EstadoReservaFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoReservaFacade() {
        super(EstadoReserva.class);
    }
    
}
