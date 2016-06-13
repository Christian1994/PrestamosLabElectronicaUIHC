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
import logica.EstudianteLogicaLocal;
import modelo.Estudiante;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author crisd
 */
@Named(value = "estudianteVista")
@RequestScoped
public class EstudianteVista {

    private InputText txtCodigo;
    private InputText txtNombre;
    private SelectOneMenu cmbPlanes;
    
    private List<Estudiante> listaEstudiantes;
    private Estudiante selectedEstudiante;

    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;

    @EJB
    private EstudianteLogicaLocal estudianteLogica;
    
    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public SelectOneMenu getCmbPlanes() {
        return cmbPlanes;
    }

    public void setCmbPlanes(SelectOneMenu cmbPlanes) {
        this.cmbPlanes = cmbPlanes;
    }

    public List<Estudiante> getListaEstudiantes() {
        if(listaEstudiantes == null){
            try {
                listaEstudiantes = estudianteLogica.consultarEstudiantes();
            } catch (Exception ex) {
                Logger.getLogger(EstudianteVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
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

    public void onRowSelect(SelectEvent event){
        this.selectedEstudiante = (Estudiante) event.getObject();
        
        this.txtCodigo.setValue(this.selectedEstudiante.getCodigo());
        this.txtNombre.setValue(this.selectedEstudiante.getNombre());
        this.cmbPlanes.setValue(this.selectedEstudiante.getPlan());
        
        this.txtCodigo.setReadonly(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);
    }

    public void limpiar(){
        this.txtCodigo.setValue("");
        this.txtNombre.setValue("");
        this.cmbPlanes.setValue("");

        this.txtCodigo.setReadonly(false);
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);        
    }

    public void action_registrar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            
            objEstudiante.setCodigo(Integer.parseInt(this.txtCodigo.getValue().toString()));
            objEstudiante.setNombre(this.txtNombre.getValue().toString());
            objEstudiante.setPlan(this.cmbPlanes.getValue().toString());
            
            estudianteLogica.registrarEstudiante(objEstudiante);            
            listaEstudiantes = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de registro de Estudiantes", "El Estudiante fue registrado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    public void action_modificar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            
            objEstudiante.setCodigo(Integer.parseInt(this.txtCodigo.getValue().toString()));
            objEstudiante.setNombre(this.txtNombre.getValue().toString());
            objEstudiante.setPlan(this.cmbPlanes.getValue().toString());
            
            estudianteLogica.modificarEstudiante(objEstudiante);            
            listaEstudiantes = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de modificación del registro de Estudiantes", "El registro de Estudiante fue modificado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    public void action_eliminar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            
            objEstudiante.setCodigo(Integer.parseInt(this.txtCodigo.getValue().toString()));
            objEstudiante.setNombre(this.txtNombre.getValue().toString());
            objEstudiante.setPlan(this.cmbPlanes.getValue().toString());
            
            estudianteLogica.eliminarEstudiante(objEstudiante);           
            listaEstudiantes = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de eliminación de registro Estudiantes", "El registro de Estudiante fue eliminado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }    
    
    /**
     * Creates a new instance of EstudianteVista
     */
    public EstudianteVista() {
    }
    
}
