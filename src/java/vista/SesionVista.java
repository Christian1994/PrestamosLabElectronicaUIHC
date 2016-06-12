/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author crisd
 */
@Named(value = "sesionVista")
@RequestScoped
public class SesionVista {

    /**
     * Creates a new instance of SesionVista
     */
    public SesionVista() {
    }
    
}
