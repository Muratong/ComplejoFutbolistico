/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansGestion;

import entidadesGestion.Cancha;
import entidadesGestion.Complejo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DelKo
 */
@Stateless
public class CanchaFacade extends AbstractFacade<Cancha> implements CanchaFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CanchaFacade() {
        super(Cancha.class);
    }

    @Override
    public List<Cancha> buscarPorComplejo(int idComplejo) {
        List<Cancha> listaCanchas = null;
        try {         
            Query consulta = em.createNamedQuery("Complejo.findByComId");
            consulta.setParameter("comId", idComplejo);
            Complejo complejo = (Complejo)consulta.getSingleResult();
            if(complejo!=null){
                listaCanchas = complejo.getCanchaList();
            }
        } catch (Exception e) {
            System.out.println("ERROR COMPLEJO NULL:" + e);
            throw e;
        }
        return listaCanchas;
    }
}
