/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.Cancha;
import entidadesReservas.Reserva;
import entidadesReservas.Usuario;
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
public class ReservaFacade extends AbstractFacade<Reserva> implements ReservaFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }

    @Override
    public List<Reserva> buscarReservasPorUsuario(Usuario usu) {
       /* List<Reserva> listaReservas = null;
        try {
            Query consulta = em.createNamedQuery("Usuario.findByUsuId");
            consulta.setParameter("usuId", usuId);
            Usuario usuario = (Usuario) consulta.getSingleResult();
            if (usuario != null) {
                listaReservas = usuario.getReservaList();
            }
        } catch (Exception e) {
            System.out.println("ERROR USUARIO NULL:" + e);
            throw e;
        }
        return listaReservas;*/
        em.flush();//nose si es necesario..
        List<Reserva> listaResUsu = null;
        try {
            Query consulta = em.createQuery("SELECT r FROM Reserva r WHERE r.resUsu= :resUsu ");
            consulta.setParameter("resUsu", usu);
            listaResUsu = consulta.getResultList();
        } catch (Exception e) {
            System.out.println("**lista vacia**");
            throw e;
        }
        return listaResUsu;
    }

    @Override
    public Reserva ultimaReservaUsuario(Usuario usu) {

        em.flush();//nose si es necesario..
        List<Reserva> listaResUsu = null;
        Reserva ultimaReserva = null;
        try {
            Query consulta = em.createQuery("SELECT r FROM Reserva r WHERE r.resUsu= :resUsu ");
            consulta.setParameter("resUsu", usu);
            listaResUsu = consulta.getResultList();
            if (listaResUsu != null) {
                ultimaReserva = listaResUsu.get(listaResUsu.size() - 1);
            } else {
                System.out.println("LISTA RESERVA NULA!!");
            }
        } catch (Exception e) {
            System.out.println("**lista vacia**");
            throw e;
        }
        return ultimaReserva;
    }

    @Override
    public Boolean verificarReservaExistente(Cancha cancha, String fecha) {
        Boolean existe = false;
        try {
            List<Reserva> listaReservas = cancha.getReservaList();
            if (listaReservas != null) {
                for (int i = 0; i < listaReservas.size(); i++) {
                    if (listaReservas.get(i).getResHoraInicio().equals(fecha)) {
                        existe = true;
                        break;
                    }
                }
            } else {
                System.out.println("LISTA RESERVA NULA!!");
            }
        } catch (Exception e) {
            System.out.println("ERROR RESERVA  NULL:" + e);
            throw e;
        }
        return existe;
    }
    
    @Override
    public List<Reserva> todasLasReservas(){
    
       List<Reserva> listaResUsu = null;
        try {
            Query consulta = em.createNamedQuery("Reserva.findAll");
            listaResUsu = consulta.getResultList();
        } catch (Exception e) {
            System.out.println("**lista vacia**");
            throw e;
        }
        return listaResUsu;
    }
}
