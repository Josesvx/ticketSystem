/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "Encargado.findByEstado", query = "SELECT e FROM Encargado e WHERE e.estado = :estado")
    , @NamedQuery(name = "Encargado.findByAudNombreCreacion", query = "SELECT e FROM Encargado e WHERE e.audNombreCreacion = :audNombreCreacion")
    , @NamedQuery(name = "Encargado.findByAudFechaCreacion", query = "SELECT e FROM Encargado e WHERE e.audFechaCreacion = :audFechaCreacion")
    , @NamedQuery(name = "Encargado.findByAudNombreModificacion", query = "SELECT e FROM Encargado e WHERE e.audNombreModificacion = :audNombreModificacion")
    , @NamedQuery(name = "Encargado.findByAudFechaModificacion", query = "SELECT e FROM Encargado e WHERE e.audFechaModificacion = :audFechaModificacion")
    , @NamedQuery(name = "Encargado.findBySolicitudE", query = "SELECT e FROM Encargado AS e JOIN e.idMantenimientoEncargado AS me WHERE me.idSolicitud.idSolicitud = :idSolicitud AND e.idDirectorio.idDirectorio = :idDirectorio")
    , @NamedQuery(name = "Encargado.findByAudStatus", query = "SELECT e FROM Encargado e WHERE e.audStatus = :audStatus")})
public class Encargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encargado", nullable = false)
    private Integer idEncargado;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @Basic(optional = false)
    @Column(name = "aud_nombre_creacion", nullable = false, length = 250)
    private String audNombreCreacion;
    @Basic(optional = false)
    @Column(name = "aud_fecha_creacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date audFechaCreacion;
    @Column(name = "aud_nombre_modificacion", length = 250)
    private String audNombreModificacion;
    @Column(name = "aud_fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date audFechaModificacion;
    @Basic(optional = false)
    @Column(name = "aud_status", nullable = false)
    private boolean audStatus;
    @JoinColumn(name = "id_directorio", referencedColumnName = "id_directorio", nullable = false)
    @ManyToOne(optional = false)
    private Directorio idDirectorio;
    @JoinColumn(name = "id_mantenimiento_encargado", referencedColumnName = "id_mantenimiento_encargado", nullable = false)
    @ManyToOne(optional = false)
    private MantenimientoEncargado idMantenimientoEncargado;

    public Encargado() {
    }

    public Encargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Encargado(Integer idEncargado, boolean estado, String audNombreCreacion, Date audFechaCreacion, boolean audStatus) {
        this.idEncargado = idEncargado;
        this.estado = estado;
        this.audNombreCreacion = audNombreCreacion;
        this.audFechaCreacion = audFechaCreacion;
        this.audStatus = audStatus;
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

    public String getAudNombreCreacion() {
        return audNombreCreacion;
    }

    public void setAudNombreCreacion(String audNombreCreacion) {
        this.audNombreCreacion = audNombreCreacion;
    }

    public Date getAudFechaCreacion() {
        return audFechaCreacion;
    }

    public void setAudFechaCreacion(Date audFechaCreacion) {
        this.audFechaCreacion = audFechaCreacion;
    }

    public String getAudNombreModificacion() {
        return audNombreModificacion;
    }

    public void setAudNombreModificacion(String audNombreModificacion) {
        this.audNombreModificacion = audNombreModificacion;
    }

    public Date getAudFechaModificacion() {
        return audFechaModificacion;
    }

    public void setAudFechaModificacion(Date audFechaModificacion) {
        this.audFechaModificacion = audFechaModificacion;
    }

    public boolean getAudStatus() {
        return audStatus;
    }

    public void setAudStatus(boolean audStatus) {
        this.audStatus = audStatus;
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
        return "sv.uesocc.edu.ingenieria.dsii2018.lacualquiera.Encargado[ idEncargado=" + idEncargado + " ]";
    }

}
