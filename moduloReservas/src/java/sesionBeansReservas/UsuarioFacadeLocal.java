/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionBeansReservas;

import entidadesReservas.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DelKo
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    
    Usuario findByNomUsu(String nomUsuario);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario findByNomUsuYClave(String nomUsu, String claveUsu);
    
    List<Usuario> listarClientes();
    
    List<Usuario> listarAdministradores();
   
}
