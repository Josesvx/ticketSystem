/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author katiro
 */
@Entity
@Table(name = "mantenimiento_encargado", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MantenimientoEncargado.findAll", query = "SELECT m FROM MantenimientoEncargado m")
    , @NamedQuery(name = "MantenimientoEncargado.findByIdMantenimientoEncargado", query = "SELECT m FROM MantenimientoEncargado m WHERE m.idMantenimientoEncargado = :idMantenimientoEncargado")
    , @NamedQuery(name = "MantenimientoEncargado.findByAudNombreCreacion", query = "SELECT m FROM MantenimientoEncargado m WHERE m.audNombreCreacion = :audNombreCreacion")
    , @NamedQuery(name = "MantenimientoEncargado.findByAudFechaCreacion", query = "SELECT m FROM MantenimientoEncargado m WHERE m.audFechaCreacion = :audFechaCreacion")
    , @NamedQuery(name = "MantenimientoEncargado.findByAudNombreModificacion", query = "SELECT m FROM MantenimientoEncargado m WHERE m.audNombreModificacion = :audNombreModificacion")
    , @NamedQuery(name = "MantenimientoEncargado.findByAudFechaModificacion", query = "SELECT m FROM MantenimientoEncargado m WHERE m.audFechaModificacion = :audFechaModificacion")
    , @NamedQuery(name = "MantenimientoEncargado.findByAudStatus", query = "SELECT m FROM MantenimientoEncargado m WHERE m.audStatus = :audStatus")})
public class MantenimientoEncargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mantenimiento_encargado", nullable = false)
    private Integer idMantenimientoEncargado;
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
    @JoinColumn(name = "id_descripcion_mantenimiento", referencedColumnName = "id_descripcion_mantenimiento")
    @ManyToOne
    private DescripcionMantenimiento idDescripcionMantenimiento;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", nullable = false)
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMantenimientoEncargado")
    private List<Encargado> encargadoList;

    public MantenimientoEncargado() {
    }

    public MantenimientoEncargado(Integer idMantenimientoEncargado) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
    }

    public MantenimientoEncargado(Integer idMantenimientoEncargado, String audNombreCreacion, Date audFechaCreacion, boolean audStatus) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
        this.audNombreCreacion = audNombreCreacion;
        this.audFechaCreacion = audFechaCreacion;
        this.audStatus = audStatus;
    }

    public Integer getIdMantenimientoEncargado() {
        return idMantenimientoEncargado;
    }

    public void setIdMantenimientoEncargado(Integer idMantenimientoEncargado) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
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

    public DescripcionMantenimiento getIdDescripcionMantenimiento() {
        return idDescripcionMantenimiento;
    }

    public void setIdDescripcionMantenimiento(DescripcionMantenimiento idDescripcionMantenimiento) {
        this.idDescripcionMantenimiento = idDescripcionMantenimiento;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @XmlTransient
    public List<Encargado> getEncargadoList() {
        return encargadoList;
    }

    public void setEncargadoList(List<Encargado> encargadoList) {
        this.encargadoList = encargadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimientoEncargado != null ? idMantenimientoEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MantenimientoEncargado)) {
            return false;
        }
        MantenimientoEncargado other = (MantenimientoEncargado) object;
        if ((this.idMantenimientoEncargado == null && other.idMantenimientoEncargado != null) || (this.idMantenimientoEncargado != null && !this.idMantenimientoEncargado.equals(other.idMantenimientoEncargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.lacualquiera.MantenimientoEncargado[ idMantenimientoEncargado=" + idMantenimientoEncargado + " ]";
    }
    
}
