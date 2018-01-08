/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.Cancha;
import entidadesReservas.Reserva;
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
     public List<Reserva> listaReservasCancha(Cancha cancha){
       em.flush();//nose si es necesario..
       List<Reserva> listaResCan = null;
        try {
            Query consulta = em.createQuery("SELECT r FROM Reserva r WHERE r.resCancha= :resCancha ");
            consulta.setParameter("resCancha", cancha);
            listaResCan = consulta.getResultList();
        } catch (Exception e) {
            System.out.println("**lista vacia**");
            throw e;
        }
         return listaResCan;
     } 
    
}
