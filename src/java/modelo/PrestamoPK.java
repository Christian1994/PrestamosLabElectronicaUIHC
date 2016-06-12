/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author crisd
 */
@Embeddable
public class PrestamoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoestudiante")
    private int codigoestudiante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "referenciaequipo")
    private String referenciaequipo;

    public PrestamoPK() {
    }

    public PrestamoPK(int codigoestudiante, String referenciaequipo) {
        this.codigoestudiante = codigoestudiante;
        this.referenciaequipo = referenciaequipo;
    }

    public int getCodigoestudiante() {
        return codigoestudiante;
    }

    public void setCodigoestudiante(int codigoestudiante) {
        this.codigoestudiante = codigoestudiante;
    }

    public String getReferenciaequipo() {
        return referenciaequipo;
    }

    public void setReferenciaequipo(String referenciaequipo) {
        this.referenciaequipo = referenciaequipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoestudiante;
        hash += (referenciaequipo != null ? referenciaequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoPK)) {
            return false;
        }
        PrestamoPK other = (PrestamoPK) object;
        if (this.codigoestudiante != other.codigoestudiante) {
            return false;
        }
        if ((this.referenciaequipo == null && other.referenciaequipo != null) || (this.referenciaequipo != null && !this.referenciaequipo.equals(other.referenciaequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PrestamoPK[ codigoestudiante=" + codigoestudiante + ", referenciaequipo=" + referenciaequipo + " ]";
    }
    
}
