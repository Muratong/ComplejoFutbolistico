/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.Rol;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "moduloGestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByNomUsuYClave(String nomUsu, String claveUsu) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.usuUsuario= ?1 AND u.usuClave= ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nomUsu);
            query.setParameter(2, claveUsu);
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            throw e;
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarClientes() {
        List<Usuario> listaClientes = null;
        try {
            Query consulta = em.createNamedQuery("Rol.findByRolNombre");
            consulta.setParameter("rolNombre", "Cliente");
            Rol rol = (Rol) consulta.getSingleResult();
            if (rol != null) {
                listaClientes = rol.getUsuarioList();
            }
        } catch (Exception e) {
            System.out.println("**ROL NULO**");
            throw e;
        }
        return listaClientes;
    }

    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> listaAdministradores = null;
        try {
            Query consulta = em.createNamedQuery("Rol.findByRolNombre");
            consulta.setParameter("rolNombre", "Administrador");
            Rol rol = (Rol) consulta.getSingleResult();
            if (rol != null) {
                listaAdministradores = rol.getUsuarioList();
            }
        } catch (Exception e) {
            System.out.println("**ROL NULO**");
            throw e;
        }
        return listaAdministradores;
    }

    @Override
    public Usuario findByNomUsu(String nomUsuario) {
        Usuario usuario = null;
        try {
            Query consulta = em.createNamedQuery("Usuario.findByUsuUsuario");
            consulta.setParameter("usuUsuario", nomUsuario);
            usuario = (Usuario) consulta.getSingleResult();
        } catch (Exception e) {
            System.out.println("**USUARIO INEXISTENTE**");
            throw e;
        }
        return usuario;
    }
   
}
