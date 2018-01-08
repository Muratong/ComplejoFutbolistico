/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.CanchaTipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DelKo
 */
@Stateless
public class CanchaTipoFacade extends AbstractFacade<CanchaTipo> implements CanchaTipoFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CanchaTipoFacade() {
        super(CanchaTipo.class);
    }
    
}
