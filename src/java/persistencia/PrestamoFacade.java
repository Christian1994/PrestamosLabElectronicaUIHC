/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Prestamo;

/**
 *
 * @author crisd
 */
@Stateless
public class PrestamoFacade extends AbstractFacade<Prestamo> implements PrestamoFacadeLocal {

    @PersistenceContext(unitName = "PrestamosLabElectronicaUIHCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamoFacade() {
        super(Prestamo.class);
    }
    
}
