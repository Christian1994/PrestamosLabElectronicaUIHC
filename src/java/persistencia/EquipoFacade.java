/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Equipo;

/**
 *
 * @author crisd
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> implements EquipoFacadeLocal {

    @PersistenceContext(unitName = "PrestamosLabElectronicaUIHCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }
    
}
