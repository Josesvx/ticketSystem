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
@Table(name = "estado_solicitud", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSolicitud.findAll", query = "SELECT e FROM EstadoSolicitud e")
    , @NamedQuery(name = "EstadoSolicitud.findByIdEstadoSolicitud", query = "SELECT e FROM EstadoSolicitud e WHERE e.idEstadoSolicitud = :idEstadoSolicitud")
    , @NamedQuery(name = "EstadoSolicitud.findByFecha", query = "SELECT e FROM EstadoSolicitud e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EstadoSolicitud.findByJustificacion", query = "SELECT e FROM EstadoSolicitud e WHERE e.justificacion = :justificacion")
    , @NamedQuery(name = "EstadoSolicitud.findByAudNombreCreacion", query = "SELECT e FROM EstadoSolicitud e WHERE e.audNombreCreacion = :audNombreCreacion")
    , @NamedQuery(name = "EstadoSolicitud.findByAudFechaCreacion", query = "SELECT e FROM EstadoSolicitud e WHERE e.audFechaCreacion = :audFechaCreacion")
    , @NamedQuery(name = "EstadoSolicitud.findByAudNombreModificacion", query = "SELECT e FROM EstadoSolicitud e WHERE e.audNombreModificacion = :audNombreModificacion")
    , @NamedQuery(name = "EstadoSolicitud.findByAudFechaModificacion", query = "SELECT e FROM EstadoSolicitud e WHERE e.audFechaModificacion = :audFechaModificacion")
    , @NamedQuery(name = "EstadoSolicitud.findByCreacion", query = "SELECT ES  FROM EstadoSolicitud AS ES WHERE ES.idEstado.idEstado=1 AND ES.idSolicitud.idSolicitud = :idSolicitud ORDER BY ES.idEstadoSolicitud DESC")    
    , @NamedQuery(name = "EstadoSolicitud.findByPausadoC", query = "SELECT  ES FROM  Estado AS E JOIN E.estadoSolicitudList AS ES WHERE ES.idSolicitud.idSolicitud = :idSolicitud  ORDER BY ES.idEstadoSolicitud DESC")                
    , @NamedQuery(name = "EstadoSolicitud.findByAudStatus", query = "SELECT e FROM EstadoSolicitud e WHERE e.audStatus = :audStatus")})
public class EstadoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_solicitud", nullable = false)
    private Integer idEstadoSolicitud;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "justificacion", length = 500)
    private String justificacion;
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
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false)
    private Estado idEstado;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", nullable = false)
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;

    public EstadoSolicitud() {
    }

    public EstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public EstadoSolicitud(Integer idEstadoSolicitud, Date fecha, String audNombreCreacion, Date audFechaCreacion, boolean audStatus) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.fecha = fecha;
        this.audNombreCreacion = audNombreCreacion;
        this.audFechaCreacion = audFechaCreacion;
        this.audStatus = audStatus;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
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

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoSolicitud != null ? idEstadoSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSolicitud)) {
            return false;
        }
        EstadoSolicitud other = (EstadoSolicitud) object;
        if ((this.idEstadoSolicitud == null && other.idEstadoSolicitud != null) || (this.idEstadoSolicitud != null && !this.idEstadoSolicitud.equals(other.idEstadoSolicitud))) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.lacualquiera.EstadoSolicitud[ idEstadoSolicitud=" + idEstadoSolicitud + " ]";
    }
    
}
