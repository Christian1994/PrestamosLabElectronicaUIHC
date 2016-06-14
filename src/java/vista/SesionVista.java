/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import logica.SesionLogicaLocal;
import modelo.Usuario;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author crisd
 */
@Named(value = "sesionVista")
@RequestScoped
public class SesionVista {

    @EJB
    private SesionLogicaLocal sesionLogica;    
    
    private InputText txtUsuario;
    private Password txtClave;
    private CommandButton btnIngresar;
    private CommandButton btnCerrar;

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public Password getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(Password txtClave) {
        this.txtClave = txtClave;
    }

    public CommandButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(CommandButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }

    public CommandButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setBtnCerrar(CommandButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }    

    public void funcion_ingresar(){
        try {            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            String urlAuxiliar = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/gestionEquipos.xhtml"));
            String urlMonitor = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/gestionPrestamos.xhtml"));
            
            String usuario = txtUsuario.getValue().toString();
            String clave = txtClave.getValue().toString();
            Usuario u = sesionLogica.iniciarSesion(usuario, clave);
            
            if(u.getTipo().equals("Auxiliar")){
                extContext.getSessionMap().put("tipo", "Auxiliar");
                extContext.getSessionMap().put("usuario", u);
                extContext.redirect(urlAuxiliar);                
            }
            else{
               if(u.getTipo().equals("Monitor")){
                    extContext.getSessionMap().put("tipo", "Monitor");
                    extContext.getSessionMap().put("usuario", u);
                    extContext.redirect(urlMonitor);                   
               }
               else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario NO existe."));                   
               }
            }
            
            // System.out.print("Usuario " + usuario + " Tipo " + extContext.getSessionMap().get("tipo"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }        
    }
    
    public void action_cerrarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext= context.getExternalContext();
            
            extContext.getSessionMap().remove("tipo");
            extContext.getSessionMap().remove("usuario");
            String url=extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,"/login.xhtml"));
            extContext.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(SesionVista.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Creates a new instance of SesionVista
     */
    public SesionVista() {
    }
    
}
