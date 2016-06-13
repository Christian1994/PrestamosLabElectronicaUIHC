/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Usuario;

/**
 *
 * @author crisd
 */
@Local
public interface UsuarioLogicaLocal {

    public void registrarUsuario(Usuario usuario) throws Exception;
    public void modificarUsuario(Usuario usuario) throws Exception;
    public void eliminarUsuario(Usuario usuario) throws Exception;
    public List<Usuario> consultarUsuarios() throws Exception;
    public String encriptarClave(String clave) throws Exception;
    
}
