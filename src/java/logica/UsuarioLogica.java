/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author crisd
 */
@Stateless
public class UsuarioLogica implements UsuarioLogicaLocal {

    @EJB
    UsuarioFacadeLocal usuarioDAO;
    
    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {
        if(usuario.getTipo().equals("Seleccione")){
            throw new Exception("Debes elegir el tipo de Usuario.");
        }
        
        Usuario objUsuario = usuarioDAO.find(usuario.getIdUsuario());
        
        if(objUsuario != null){
            throw new Exception("Usuario con ese nombre ya existe.");
        }
        else{
            String claveEncriptada = this.encriptarClave(usuario.getClave());
            usuario.setClave(claveEncriptada);
            usuarioDAO.create(usuario);
        }
    }

    @Override
    public void modificarUsuario(Usuario usuario) throws Exception {
        if(usuario.getTipo().equals("Seleccione")){
            throw new Exception("Debes elegir el tipo de Usuario;");
        }
        
        if(!usuario.getClave().equals("")){
            String claveEncriptada = this.encriptarClave(usuario.getClave());
            usuario.setClave(claveEncriptada);            
        }
        
        usuarioDAO.edit(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) throws Exception {
        usuarioDAO.remove(usuario);
    }

    @Override
    public List<Usuario> consultarUsuarios() throws Exception {
        return usuarioDAO.findAll();
    }    

    @Override
    public String encriptarClave(String clave) throws Exception {
        String claveEncriptada = DigestUtils.md5Hex(clave);
        return claveEncriptada;
    }


}
