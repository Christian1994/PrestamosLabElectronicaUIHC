/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import persistencia.EquipoFacadeLocal;
import persistencia.EstudianteFacadeLocal;
import persistencia.PrestamoFacadeLocal;

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
    private EstudianteFacadeLocal estudianteDAO;
    
    @EJB
    private EquipoFacadeLocal equipoDAO;
    
    @EJB
    private PrestamoFacadeLocal prestamoDAO;
    
    public SelectOneMenu getCmbEstudiantes() {
        return cmbEstudiantes;
    }

    public void setCmbEstudiantes(SelectOneMenu cmbEstudiantes) {
        this.cmbEstudiantes = cmbEstudiantes;
    }

    public ArrayList<SelectItem> getItemsEstudiantes() {
        List<Estudiante> itemsEs = estudianteDAO.findAll();
        this.itemsEstudiantes = new ArrayList<>();
        
        itemsEs.stream().forEach((es) -> {
            this.itemsEstudiantes.add(new SelectItem(es.getCodigo(), es.getNombre()));
        });
        
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
        List<Equipo> itemsEq = equipoDAO.findAll();
        this.itemsEquipos = new ArrayList<>();
        
        itemsEq.stream().forEach((eq) -> {
            this.itemsEquipos.add(new SelectItem(eq.getReferencia(), eq.getNombre()));
        });
        
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
            listaPrestamos = prestamoDAO.findAll();
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
        
        this.cmbEstudiantes.setReadonly(true);
        this.cmbEquipos.setReadonly(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);        
    }
    
    public void limpiar(){
        this.cmbEstudiantes.setValue("");
        this.cmbEquipos.setValue("");
        this.txtFechaDevolucion = null;
        this.txtObservaciones.setValue("");
        
        this.cmbEstudiantes.setReadonly(true);
        this.cmbEquipos.setReadonly(true);
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);        
    }
    
    public void action_registrar(){
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
        
        prestamoDAO.create(objPrestamo);
        listaPrestamos = null;
        limpiar();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de registro de Préstamo", "El Préstamo fue registrado con éxito."));        
    }
    
    public void action_modificar(){
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
        
        prestamoDAO.edit(objPrestamo);
        listaPrestamos = null;
        limpiar();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de modificación de Préstamo", "El Préstamo fue modificado con éxito."));        
    }
    
    public void action_eliminar(){
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
        
        prestamoDAO.remove(objPrestamo);
        listaPrestamos = null;
        limpiar();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Información de eliminación de Préstamo", "El Préstamo fue eliminado con éxito."));        
    }
    
    /**
     * Creates a new instance of PrestamoVista
     */
    public PrestamoVista() {
    }
    
}
