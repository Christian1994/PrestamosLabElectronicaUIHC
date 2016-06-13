/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Prestamo;
import modelo.PrestamoPK;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
import logica.EquipoLogicaLocal;
import logica.EstudianteLogicaLocal;
import logica.PrestamoLogicaLocal;

/**
 *
 * @author crisd
 */
@Named(value = "prestamoVista")
@RequestScoped
public class PrestamoVista {

    private SelectOneMenu cmbEstudiantes;
    private ArrayList<SelectItem> itemsEstudiantes;
    
    private SelectOneMenu cmbEquipos;
    private ArrayList<SelectItem> itemsEquipos;
    
    private Date txtFechaDevolucion;
    private InputTextarea txtObservaciones;

    private List<Prestamo> listaPrestamos;
    private Prestamo selectedPrestamo;

    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;    
    
    @EJB
    private EstudianteLogicaLocal estudianteLogica;
    
    @EJB
    private EquipoLogicaLocal equipoLogica;
    
    @EJB
    private PrestamoLogicaLocal prestamoLogica;
    
    public SelectOneMenu getCmbEstudiantes() {
        return cmbEstudiantes;
    }

    public void setCmbEstudiantes(SelectOneMenu cmbEstudiantes) {
        this.cmbEstudiantes = cmbEstudiantes;
    }

    public ArrayList<SelectItem> getItemsEstudiantes() {
        try {
            List<Estudiante> itemsEs = estudianteLogica.consultarEstudiantes();
            this.itemsEstudiantes = new ArrayList<>();
            
            itemsEs.stream().forEach((es) -> {
                this.itemsEstudiantes.add(new SelectItem(es.getCodigo(), es.getNombre()));
            });            
        } catch (Exception ex) {
            Logger.getLogger(PrestamoVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemsEstudiantes;
    }

    public void setItemsEstudiantes(ArrayList<SelectItem> itemsEstudiantes) {
        this.itemsEstudiantes = itemsEstudiantes;
    }

    public SelectOneMenu getCmbEquipos() {
        return cmbEquipos;
    }

    public void setCmbEquipos(SelectOneMenu cmbEquipos) {
        this.cmbEquipos = cmbEquipos;
    }

    public ArrayList<SelectItem> getItemsEquipos() {
        try {
            List<Equipo> itemsEq = equipoLogica.consultarInventario();
            this.itemsEquipos = new ArrayList<>();
            
            itemsEq.stream().forEach((eq) -> {
                this.itemsEquipos.add(new SelectItem(eq.getReferencia(), eq.getNombre()));
            });            
        } catch (Exception ex) {
            Logger.getLogger(PrestamoVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemsEquipos;        
    }

    public void setItemsEquipos(ArrayList<SelectItem> itemsEquipos) {
        this.itemsEquipos = itemsEquipos;
    }

    public Date getTxtFechaDevolucion() {
        return txtFechaDevolucion;
    }

    public void setTxtFechaDevolucion(Date txtFechaDevolucion) {
        this.txtFechaDevolucion = txtFechaDevolucion;
    }

    public InputTextarea getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setTxtObservaciones(InputTextarea txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }

    public List<Prestamo> getListaPrestamos() {
        if(listaPrestamos == null){
            try {
                listaPrestamos = prestamoLogica.consultarPrestamos();
            } catch (Exception ex) {
                Logger.getLogger(PrestamoVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public Prestamo getSelectedPrestamo() {
        return selectedPrestamo;
    }

    public void setSelectedPrestamo(Prestamo selectedPrestamo) {
        this.selectedPrestamo = selectedPrestamo;
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
        this.selectedPrestamo = (Prestamo) event.getObject();
        
        this.cmbEstudiantes.setValue(this.selectedPrestamo.getEstudiante().getCodigo());
        this.cmbEquipos.setValue(this.selectedPrestamo.getEquipo().getReferencia());
        this.txtFechaDevolucion = this.selectedPrestamo.getFechadevolucion();
        this.txtObservaciones.setValue(this.selectedPrestamo.getObservaciones());
        
        this.cmbEstudiantes.setDisabled(true);
        this.cmbEquipos.setDisabled(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);        
    }
    
    public void limpiar(){
        this.cmbEstudiantes.setValue("");
        this.cmbEquipos.setValue("");
        this.txtFechaDevolucion = null;
        this.txtObservaciones.setValue("");
        
        this.cmbEstudiantes.setDisabled(false);
        this.cmbEquipos.setDisabled(false);
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);        
    }
    
    public void action_registrar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setCodigo(Integer.parseInt(this.cmbEstudiantes.getValue().toString()));
            
            Equipo objEquipo = new Equipo();
            objEquipo.setReferencia(this.cmbEquipos.getValue().toString());
            
            Prestamo objPrestamo = new Prestamo();
            PrestamoPK objPrestamoPK = new PrestamoPK(objEstudiante.getCodigo(), objEquipo.getReferencia());
            objPrestamo.setPrestamoPK(objPrestamoPK);
            objPrestamo.setEstudiante(objEstudiante);
            objPrestamo.setEquipo(objEquipo);
            objPrestamo.setFechadevolucion(txtFechaDevolucion);
            objPrestamo.setObservaciones(this.txtObservaciones.getValue().toString());
            
            prestamoLogica.registrarPrestamo(objPrestamo);
            listaPrestamos = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de registro de Préstamo", "El Préstamo fue registrado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    public void action_modificar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setCodigo(Integer.parseInt(this.cmbEstudiantes.getValue().toString()));
            
            Equipo objEquipo = new Equipo();
            objEquipo.setReferencia(this.cmbEquipos.getValue().toString());
            
            Prestamo objPrestamo = new Prestamo();
            PrestamoPK objPrestamoPK = new PrestamoPK(objEstudiante.getCodigo(), objEquipo.getReferencia());
            objPrestamo.setPrestamoPK(objPrestamoPK);
            objPrestamo.setEstudiante(objEstudiante);
            objPrestamo.setEquipo(objEquipo);
            objPrestamo.setFechadevolucion(txtFechaDevolucion);
            objPrestamo.setObservaciones(this.txtObservaciones.getValue().toString());
            
            prestamoLogica.modificarPrestamo(objPrestamo);
            listaPrestamos = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de modificación de Préstamo", "El Préstamo fue modificado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    public void action_eliminar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setCodigo(Integer.parseInt(this.cmbEstudiantes.getValue().toString()));
            
            Equipo objEquipo = new Equipo();
            objEquipo.setReferencia(this.cmbEquipos.getValue().toString());
            
            Prestamo objPrestamo = new Prestamo();
            PrestamoPK objPrestamoPK = new PrestamoPK(objEstudiante.getCodigo(), objEquipo.getReferencia());
            objPrestamo.setPrestamoPK(objPrestamoPK);
            objPrestamo.setEstudiante(objEstudiante);
            objPrestamo.setEquipo(objEquipo);
            objPrestamo.setFechadevolucion(txtFechaDevolucion);
            objPrestamo.setObservaciones(this.txtObservaciones.getValue().toString());
            
            prestamoLogica.eliminarPrestamo(objPrestamo);
            listaPrestamos = null;
            limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,        
                    "Información de eliminación de Préstamo", "El Préstamo fue eliminado con éxito."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,        
                    "Error", ex.getMessage()));
        }
    }
    
    /**
     * Creates a new instance of PrestamoVista
     */
    public PrestamoVista() {
    }
    
}
