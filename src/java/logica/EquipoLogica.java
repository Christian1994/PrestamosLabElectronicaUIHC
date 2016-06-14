/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Equipo;
import persistencia.EquipoFacadeLocal;

/**
 *
 * @author crisd
 */
@Stateless
public class EquipoLogica implements EquipoLogicaLocal {

    @EJB
    EquipoFacadeLocal equipoDAO;
    
    @Override
    public void registrarEquipo(Equipo equipo) throws Exception {
        if(equipo.getEstado().equals("Seleccione")){
            throw new Exception("Debes seleccionar el estado actual del Equipo.");
        }        
        
        Equipo objEquipo = equipoDAO.find(equipo.getReferencia());
        
        if(objEquipo != null){
            throw new Exception("Equipo ya existe con esa referencia.");     
        }
        else{
            equipoDAO.create(equipo);
        }
    }

    @Override
    public void modificarEquipo(Equipo equipo) throws Exception {
        if(equipo.getEstado().equals("Seleccione")){
            throw new Exception("Debes seleccionar el estado actual del Equipo.");
        }
        
        Equipo objEquipo = equipoDAO.find(equipo.getReferencia());
        
        if(objEquipo.getPrestamoList().size() > 0){
            throw new Exception("No se puede modificar la información del Equipo hasta que éste sea devuelto.");
        }
        
        equipoDAO.edit(equipo);
    }

    @Override
    public void eliminarEquipo(Equipo equipo) throws Exception {
        Equipo objEquipo = equipoDAO.find(equipo.getReferencia());
        
        if(objEquipo.getPrestamoList().size() > 0){
            throw new Exception("El Equipo está prestado. Podrá ser eliminado del Inventario cuando el préstamo se elimine primero.");
        }
        
        equipoDAO.remove(equipo);
    }

    @Override
    public void evaluarEstadoEquipo(Equipo equipo) throws Exception {
        Equipo objEquipo = equipoDAO.find(equipo.getReferencia());        
        
        if(objEquipo.getEstado().equals("Mantenimiento")){
            throw new Exception("El Equipo se encuentra en Mantenimiento.");
        }
        if(objEquipo.getEstado().equals("Dañado")){
            throw new Exception("El Equipo se encuentra Dañado.");
        }
        if(objEquipo.getEstado().equals("En Préstamo")){
            throw new Exception("El Equipo ya se encuentra En Préstamo.");
        }
    }      
    
    @Override
    public Equipo consultarxReferencia(String referencia) throws Exception {
        return equipoDAO.find(referencia);
    }

    @Override
    public List<Equipo> consultarInventario() throws Exception {
        return equipoDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
