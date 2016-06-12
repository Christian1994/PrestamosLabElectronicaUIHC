/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crisd
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByCodigoestudiante", query = "SELECT p FROM Prestamo p WHERE p.prestamoPK.codigoestudiante = :codigoestudiante"),
    @NamedQuery(name = "Prestamo.findByReferenciaequipo", query = "SELECT p FROM Prestamo p WHERE p.prestamoPK.referenciaequipo = :referenciaequipo"),
    @NamedQuery(name = "Prestamo.findByFechadevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechadevolucion = :fechadevolucion"),
    @NamedQuery(name = "Prestamo.findByObservaciones", query = "SELECT p FROM Prestamo p WHERE p.observaciones = :observaciones")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamoPK prestamoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechadevolucion;
    @Size(max = 200)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "codigoestudiante", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiante estudiante;
    @JoinColumn(name = "referenciaequipo", referencedColumnName = "referencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;

    public Prestamo() {
    }

    public Prestamo(PrestamoPK prestamoPK) {
        this.prestamoPK = prestamoPK;
    }

    public Prestamo(PrestamoPK prestamoPK, Date fechadevolucion) {
        this.prestamoPK = prestamoPK;
        this.fechadevolucion = fechadevolucion;
    }

    public Prestamo(int codigoestudiante, String referenciaequipo) {
        this.prestamoPK = new PrestamoPK(codigoestudiante, referenciaequipo);
    }

    public PrestamoPK getPrestamoPK() {
        return prestamoPK;
    }

    public void setPrestamoPK(PrestamoPK prestamoPK) {
        this.prestamoPK = prestamoPK;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prestamoPK != null ? prestamoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.prestamoPK == null && other.prestamoPK != null) || (this.prestamoPK != null && !this.prestamoPK.equals(other.prestamoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Prestamo[ prestamoPK=" + prestamoPK + " ]";
    }
    
}
