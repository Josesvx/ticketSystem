/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author katiro
 */
@Entity
@Table(name = "solicitud", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByTitulo", query = "SELECT s FROM Solicitud s WHERE s.titulo = :titulo")
    , @NamedQuery(name = "Solicitud.findByDescripcion", query = "SELECT s FROM Solicitud s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "Solicitud.findByAdjunto", query = "SELECT s FROM Solicitud s WHERE s.adjunto = :adjunto")
    , @NamedQuery(name = "Solicitud.findByNSeguimiento", query = "SELECT s FROM Solicitud s WHERE s.nSeguimiento = :nSeguimiento")
    , @NamedQuery(name = "Solicitud.findByFeedback", query = "SELECT s FROM Solicitud s WHERE s.feedback = :feedback")
    , @NamedQuery(name = "Solicitud.findByCorrelativo", query = "SELECT s FROM Solicitud s WHERE s.correlativo = :correlativo")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 250)
    @Column(name = "adjunto")
    private String adjunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "n_seguimiento")
    private String nSeguimiento;
    @Size(max = 500)
    @Column(name = "feedback")
    private String feedback;
    @Size(max = 10)
    @Column(name = "correlativo")
    private String correlativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<MantenimientoEncargado> mantenimientoEncargadoList;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private Categoria idCategoria;
    @JoinColumn(name = "id_directorio", referencedColumnName = "id_directorio")
    @ManyToOne(optional = false)
    private Directorio idDirectorio;
    @JoinColumn(name = "id_prioridad", referencedColumnName = "id_prioridad")
    @ManyToOne
    private Prioridad idPrioridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<EstadoSolicitud> estadoSolicitudList;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Solicitud(Integer idSolicitud, String titulo, String descripcion, String nSeguimiento) {
        this.idSolicitud = idSolicitud;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nSeguimiento = nSeguimiento;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getNSeguimiento() {
        return nSeguimiento;
    }

    public void setNSeguimiento(String nSeguimiento) {
        this.nSeguimiento = nSeguimiento;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    @XmlTransient
    public List<MantenimientoEncargado> getMantenimientoEncargadoList() {
        return mantenimientoEncargadoList;
    }

    public void setMantenimientoEncargadoList(List<MantenimientoEncargado> mantenimientoEncargadoList) {
        this.mantenimientoEncargadoList = mantenimientoEncargadoList;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Directorio getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(Directorio idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public Prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    @XmlTransient
    public List<EstadoSolicitud> getEstadoSolicitudList() {
        return estadoSolicitudList;
    }

    public void setEstadoSolicitudList(List<EstadoSolicitud> estadoSolicitudList) {
        this.estadoSolicitudList = estadoSolicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
