/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Estudiante;
import persistencia.EstudianteFacadeLocal;

/**
 *
 * @author crisd
 */
@Stateless
public class EstudianteLogica implements EstudianteLogicaLocal {

    @EJB
    EstudianteFacadeLocal estudianteDAO;
    
    @Override
    public void registrarEstudiante(Estudiante estudiante) throws Exception {
        if(estudiante.getPlan().equals("Seleccione")){
            throw new Exception("Debes seleccionar un Plan.");
        }
        
        Estudiante objEstudiante = estudianteDAO.find(estudiante.getCodigo());
        
        if(objEstudiante != null){
            throw new Exception("Estudiante ya existe con esa referencia.");     
        }
        else{
            estudianteDAO.create(estudiante);
        }
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) throws Exception {
        if(estudiante.getPlan().equals("Seleccione")){
            throw new Exception("Debes seleccionar un Plan.");
        }        
        
        estudianteDAO.edit(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) throws Exception {
        Estudiante objEstudiante = estudianteDAO.find(estudiante.getCodigo());
        
        if(objEstudiante.getPrestamoList().size() > 0){
            throw new Exception("El Estudiante aún tiene equipos prestados. Podrá ser eliminado de la lista cuando realice sus devoluciones.");
        }
        
        estudianteDAO.remove(estudiante);       
    }

    @Override
    public Estudiante consultarxCodigo(Integer codigo) throws Exception {
        return estudianteDAO.find(codigo);
    }

    @Override
    public List<Estudiante> consultarEstudiantes() throws Exception {
        return estudianteDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
