/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Equipo;

/**
 *
 * @author crisd
 */
@Local
public interface EquipoLogicaLocal {

    public void registrarEquipo(Equipo equipo) throws Exception;
    public void modificarEquipo(Equipo equipo) throws Exception;
    public void eliminarEquipo(Equipo equipo) throws Exception;
    public void evaluarEstadoEquipo(Equipo equipo) throws Exception;
    public Equipo consultarxReferencia(String referencia) throws Exception;
    public List<Equipo> consultarInventario() throws Exception;
    
}
