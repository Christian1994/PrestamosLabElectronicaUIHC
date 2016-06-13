/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Prestamo;
import persistencia.PrestamoFacadeLocal;

/**
 *
 * @author crisd
 */
@Stateless
public class PrestamoLogica implements PrestamoLogicaLocal {

    @EJB
    PrestamoFacadeLocal prestamoDAO;
    
    @Override
    public void registrarPrestamo(Prestamo prestamo) throws Exception {
        if(prestamo.getEstudiante() == null){
            throw new Exception("Debes seleccionar un Estudiante.");
        }
        if(prestamo.getEquipo() == null){
            throw new Exception("Debes seleccionar un Equipo.");
        }
        
        Prestamo objPrestamo = prestamoDAO.find(prestamo.getPrestamoPK());
        
        if(objPrestamo != null){
            throw new Exception("Préstamo ya existe.");
        }
        else{
            prestamoDAO.create(prestamo);           
        }
    }

    @Override
    public void modificarPrestamo(Prestamo prestamo) throws Exception {        
        prestamoDAO.edit(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) throws Exception {
        prestamoDAO.remove(prestamo);
    }

    @Override
    public Prestamo consultarxReferencia(String referencia) throws Exception {
        return prestamoDAO.find(referencia);
    }

    @Override
    public List<Prestamo> consultarPrestamos() throws Exception {
        return prestamoDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
