/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author katiro
 */
@Entity
@Table(name = "encargado", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargado.findAll", query = "SELECT e FROM Encargado e")
    , @NamedQuery(name = "Encargado.findByIdEncargado", query = "SELECT e FROM Encargado e WHERE e.idEncargado = :idEncargado")
    , @NamedQuery(name = "Encargado.findByEstado", query = "SELECT e FROM Encargado e WHERE e.estado = :estado")})
public class Encargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encargado")
    private Integer idEncargado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_directorio", referencedColumnName = "id_directorio")
    @ManyToOne(optional = false)
    private Directorio idDirectorio;
    @JoinColumn(name = "id_mantenimiento_encargado", referencedColumnName = "id_mantenimiento_encargado")
    @ManyToOne(optional = false)
    private MantenimientoEncargado idMantenimientoEncargado;

    public Encargado() {
    }

    public Encargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Encargado(Integer idEncargado, boolean estado) {
        this.idEncargado = idEncargado;
        this.estado = estado;
    }

    public Integer getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Directorio getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(Directorio idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public MantenimientoEncargado getIdMantenimientoEncargado() {
        return idMantenimientoEncargado;
    }

    public void setIdMantenimientoEncargado(MantenimientoEncargado idMantenimientoEncargado) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncargado != null ? idEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargado)) {
            return false;
        }
        Encargado other = (Encargado) object;
        if ((this.idEncargado == null && other.idEncargado != null) || (this.idEncargado != null && !this.idEncargado.equals(other.idEncargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Encargado[ idEncargado=" + idEncargado + " ]";
    }
    
}
