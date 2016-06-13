/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Prestamo;

/**
 *
 * @author crisd
 */
@Local
public interface PrestamoLogicaLocal {
    
    public void registrarPrestamo(Prestamo prestamo) throws Exception;
    public void modificarPrestamo(Prestamo prestamo) throws Exception;
    public void eliminarPrestamo(Prestamo prestamo) throws Exception;
    public Prestamo consultarxReferencia(String referencia) throws Exception;
    public List<Prestamo> consultarPrestamos() throws Exception;
    
}
