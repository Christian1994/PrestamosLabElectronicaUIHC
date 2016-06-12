/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Equipo;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
import persistencia.EquipoFacadeLocal;

/**
 *
 * @author crisd
 */
@Named(value = "equipoVista")
@RequestScoped
public class EquipoVista {

    private InputText txtReferencia;
    private InputText txtNombre;
    private SelectOneMenu cmbEstados;
    
    private List<Equipo> inventario;
    private Equipo selectedEquipo;

    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;

    @EJB
    EquipoFacadeLocal equipoDAO;
    
    public InputText getTxtReferencia() {
        return txtReferencia;
    }

    public void setTxtReferencia(InputText txtReferencia) {
        this.txtReferencia = txtReferencia;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public SelectOneMenu getCmbEstados() {
        return cmbEstados;
    }

    public void setCmbEstados(SelectOneMenu cmbEstados) {
        this.cmbEstados = cmbEstados;
    }

    public List<Equipo> getInventario() {
        if(inventario == null){
            inventario = equipoDAO.findAll();
        }
        return inventario;
    }

    public void setInventario(List<Equipo> inventario) {
        this.inventario = inventario;
    }

    public Equipo getSelectedEquipo() {
        return selectedEquipo;
    }

    public void setSelectedEquipo(Equipo selectedEquipo) {
        this.selectedEquipo = selectedEquipo;
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
        this.selectedEquipo = (Equipo) event.getObject();
        
        this.txtReferencia.setValue(this.selectedEquipo.getReferencia());
        this.txtNombre.setValue(this.selectedEquipo.getNombre());
        this.cmbEstados.setValue(this.selectedEquipo.getEstado());
        
        this.txtReferencia.setReadonly(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);
    }

    public void limpiar(){
        this.txtReferencia.setValue("");
        this.txtNombre.setValue("");
        this.cmbEstados.setValue("");

        this.txtReferencia.setReadonly(false);
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);        
    }

    public void action_registrar(){
        Equipo objEquipo = new Equipo();
        
        objEquipo.setReferencia(this.txtReferencia.getValue().toString());
        objEquipo.setNombre(this.txtNombre.getValue().toString());
        objEquipo.setEstado(this.cmbEstados.getValue().toString());
        
        equipoDAO.create(objEquipo);

        inventario = null;
        limpiar();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de registro de Equipos", "El Equipo fue registrado en el Inventario con éxito."));        
    }
    
    public void action_modificar(){
        Equipo objEquipo = new Equipo();
        
        objEquipo.setReferencia(this.txtReferencia.getValue().toString());
        objEquipo.setNombre(this.txtNombre.getValue().toString());
        objEquipo.setEstado(this.cmbEstados.getValue().toString());
        
        equipoDAO.edit(objEquipo);

        inventario = null;
        limpiar();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de modificación del registro de Equipos", "El registro de Equipo fue modificado con éxito."));        
    }
    
    public void action_eliminar(){
        Equipo objEquipo = new Equipo();
        
        objEquipo.setReferencia(this.txtReferencia.getValue().toString());
        objEquipo.setNombre(this.txtNombre.getValue().toString());
        objEquipo.setEstado(this.cmbEstados.getValue().toString());
        
        equipoDAO.remove(objEquipo);

        inventario = null;
        limpiar();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de eliminación de Equipos", "El Equipo fue eliminado del Inventario con éxito."));        
    }
    
    /**
     * Creates a new instance of EquipoVista
     */
    public EquipoVista() {
    }
    
}
