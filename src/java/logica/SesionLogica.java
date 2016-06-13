/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author jsnar
 */
@Stateless
public class SesionLogica implements SesionLogicaLocal {

    @EJB
    private UsuarioFacadeLocal usuarioDAO;
    
    @Override
    public Usuario iniciarSesion(String usuario, String clave) throws Exception {
        if(usuario == null || clave == null || clave.equals("")){
            throw new Exception("Los datos de ingreso son Obligatorios.");
        }
        
        Usuario u = usuarioDAO.find(usuario);
        
        if(u != null){
            String claveEncriptada = DigestUtils.md5Hex(clave);
            if(!u.getClave().equals(claveEncriptada)){
                throw new Exception("Clave incorrecta.");
            }
        }
        
        return u;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}