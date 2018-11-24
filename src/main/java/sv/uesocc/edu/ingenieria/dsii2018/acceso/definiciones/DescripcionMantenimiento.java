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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "descripcion_mantenimiento", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionMantenimiento.findAll", query = "SELECT d FROM DescripcionMantenimiento d")
    , @NamedQuery(name = "DescripcionMantenimiento.findByIdDescripcionMantenimiento", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.idDescripcionMantenimiento = :idDescripcionMantenimiento")
    , @NamedQuery(name = "DescripcionMantenimiento.findByDescripcionProblema", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.descripcionProblema = :descripcionProblema")
    , @NamedQuery(name = "DescripcionMantenimiento.findByDescripcionSolucion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.descripcionSolucion = :descripcionSolucion")
    , @NamedQuery(name = "DescripcionMantenimiento.findByAudNombreCreacion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.audNombreCreacion = :audNombreCreacion")
    , @NamedQuery(name = "DescripcionMantenimiento.findByAudFechaCreacion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.audFechaCreacion = :audFechaCreacion")
    , @NamedQuery(name = "DescripcionMantenimiento.findByAudNombreModificacion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.audNombreModificacion = :audNombreModificacion")
    , @NamedQuery(name = "DescripcionMantenimiento.findByAudFechaModificacion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.audFechaModificacion = :audFechaModificacion")
    , @NamedQuery(name = "DescripcionMantenimiento.finBySolicitudEncargado", query = "SELECT  dm FROM DescripcionMantenimiento AS dm JOIN dm.mantenimientoEncargadoList as me JOIN me.encargadoList AS e WHERE e.idDirectorio.idDirectorio= :idDirectorio AND me.idSolicitud.idSolicitud= :idSolicitud")   
    , @NamedQuery(name = "DescripcionMantenimiento.findByCorrelativo", query = "SELECT dm FROM DescripcionMantenimiento AS dm JOIN dm.mantenimientoEncargadoList AS me JOIN me.idSolicitud AS s WHERE s.correlativo = :correlativo")    
    , @NamedQuery(name = "DescripcionMantenimiento.findByAudStatus", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.audStatus = :audStatus")})
public class DescripcionMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descripcion_mantenimiento", nullable = false)
    private Integer idDescripcionMantenimiento;
    @Column(name = "descripcion_problema", length = 500)
    private String descripcionProblema;
    @Column(name = "descripcion_solucion", length = 500)
    private String descripcionSolucion;
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
    @OneToMany(mappedBy = "idDescripcionMantenimiento")
    private List<MantenimientoEncargado> mantenimientoEncargadoList;

    public DescripcionMantenimiento() {
    }

    public DescripcionMantenimiento(Integer idDescripcionMantenimiento) {
        this.idDescripcionMantenimiento = idDescripcionMantenimiento;
    }

    public DescripcionMantenimiento(Integer idDescripcionMantenimiento, String audNombreCreacion, Date audFechaCreacion, boolean audStatus) {
        this.idDescripcionMantenimiento = idDescripcionMantenimiento;
        this.audNombreCreacion = audNombreCreacion;
        this.audFechaCreacion = audFechaCreacion;
        this.audStatus = audStatus;
    }

    public Integer getIdDescripcionMantenimiento() {
        return idDescripcionMantenimiento;
    }

    public void setIdDescripcionMantenimiento(Integer idDescripcionMantenimiento) {
        this.idDescripcionMantenimiento = idDescripcionMantenimiento;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getDescripcionSolucion() {
        return descripcionSolucion;
    }

    public void setDescripcionSolucion(String descripcionSolucion) {
        this.descripcionSolucion = descripcionSolucion;
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

    @XmlTransient
    public List<MantenimientoEncargado> getMantenimientoEncargadoList() {
        return mantenimientoEncargadoList;
    }

    public void setMantenimientoEncargadoList(List<MantenimientoEncargado> mantenimientoEncargadoList) {
        this.mantenimientoEncargadoList = mantenimientoEncargadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescripcionMantenimiento != null ? idDescripcionMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionMantenimiento)) {
            return false;
        }
        DescripcionMantenimiento other = (DescripcionMantenimiento) object;
        if ((this.idDescripcionMantenimiento == null && other.idDescripcionMantenimiento != null) || (this.idDescripcionMantenimiento != null && !this.idDescripcionMantenimiento.equals(other.idDescripcionMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.lacualquiera.DescripcionMantenimiento[ idDescripcionMantenimiento=" + idDescripcionMantenimiento + " ]";
    }
    
}
