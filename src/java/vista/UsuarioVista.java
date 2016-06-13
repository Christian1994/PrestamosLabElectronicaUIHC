/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.UsuarioLogicaLocal;
import modelo.Usuario;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author crisd
 */
@Named(value = "usuarioVista")
@RequestScoped
public class UsuarioVista {

    private InputText txtIdUsuario;
    private InputText txtNombresCompletos;
    private SelectOneMenu cmbTipos;
    private String txtClave;

    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;    

    private Usuario selectedUsuario;
    private List<Usuario> listaUsuarios;
    
    @EJB
    UsuarioLogicaLocal usuarioLogica;
    
    public InputText getTxtIdUsuario() {
        return txtIdUsuario;
    }

    public void setTxtIdUsuario(InputText txtIdUsuario) {
        this.txtIdUsuario = txtIdUsuario;
    }

    public InputText getTxtNombresCompletos() {
        return txtNombresCompletos;
    }

    public void setTxtNombresCompletos(InputText txtNombresCompletos) {
        this.txtNombresCompletos = txtNombresCompletos;
    }

    public SelectOneMenu getCmbTipos() {
        return cmbTipos;
    }

    public void setCmbTipos(SelectOneMenu cmbTipos) {
        this.cmbTipos = cmbTipos;
    }

    public String getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(String txtClave) {
        this.txtClave = txtClave;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        if(listaUsuarios == null){
            try {
                listaUsuarios = usuarioLogica.consultarUsuarios();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void onRowSelect(SelectEvent event){
        this.selectedUsuario = (Usuario) event.getObject();
        
        this.txtIdUsuario.setValue(this.selectedUsuario.getIdUsuario());
        this.txtNombresCompletos.setValue(this.selectedUsuario.getNombrescompletos());
        this.cmbTipos.setValue(this.selectedUsuario.getTipo());
        this.txtClave = this.selectedUsuario.getClave();
        
        this.txtIdUsuario.setReadonly(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);
    }

    public void limpiar(){
        this.txtIdUsuario.setValue("");
        this.txtNombresCompletos.setValue("");
        this.cmbTipos.setValue("");
        this.txtClave = null;
        
        this.txtIdUsuario.setReadonly(false);
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);        
    }
    
    public void action_registrar(){
        try {
            Usuario objUsuario = new Usuario();
            
            objUsuario.setIdUsuario(this.txtIdUsuario.getValue().toString());
            objUsuario.setNombrescompletos(this.txtNombresCompletos.getValue().toString());
            objUsuario.setTipo(this.cmbTipos.getValue().toString());
            objUsuario.setClave(txtClave);
            
            usuarioLogica.registrarUsuario(objUsuario);
            listaUsuarios = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de registro de Usuario", "El Usuario fue registrado con éxito."));            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    public void action_modificar(){
        try {
            Usuario objUsuario = new Usuario();
            
            objUsuario.setIdUsuario(this.txtIdUsuario.getValue().toString());
            objUsuario.setNombrescompletos(this.txtNombresCompletos.getValue().toString());
            objUsuario.setTipo(this.cmbTipos.getValue().toString());
            objUsuario.setClave(txtClave);
            
            usuarioLogica.modificarUsuario(objUsuario);
            listaUsuarios = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de modificación de Usuario", "El Usuario fue modificado con éxito."));            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }        
    }
    
    public void action_eliminar(){
        try {
            Usuario objUsuario = new Usuario();
            
            objUsuario.setIdUsuario(this.txtIdUsuario.getValue().toString());
            objUsuario.setNombrescompletos(this.txtNombresCompletos.getValue().toString());
            objUsuario.setTipo(this.cmbTipos.getValue().toString());
            objUsuario.setClave(txtClave);
            
            usuarioLogica.registrarUsuario(objUsuario);
            listaUsuarios = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de eliminación de Usuario", "El Usuario fue eliminado con éxito."));            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }        
    }
    
    /**
     * Creates a new instance of UsuarioMonitorVista
     */
    public UsuarioVista() {
    }
    
}
