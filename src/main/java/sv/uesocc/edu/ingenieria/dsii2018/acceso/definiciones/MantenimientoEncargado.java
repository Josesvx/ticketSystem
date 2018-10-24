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
    , @NamedQuery(name = "MantenimientoEncargado.findByIdMantenimientoEncargado", query = "SELECT m FROM MantenimientoEncargado m WHERE m.idMantenimientoEncargado = :idMantenimientoEncargado")})
public class MantenimientoEncargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mantenimiento_encargado")
    private Integer idMantenimientoEncargado;
    @JoinColumn(name = "id_descripcion_mantenimiento", referencedColumnName = "id_descripcion_mantenimiento")
    @ManyToOne
    private DescripcionMantenimiento idDescripcionMantenimiento;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMantenimientoEncargado")
    private List<Encargado> encargadoList;

    public MantenimientoEncargado() {
    }

    public MantenimientoEncargado(Integer idMantenimientoEncargado) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
    }

    public Integer getIdMantenimientoEncargado() {
        return idMantenimientoEncargado;
    }

    public void setIdMantenimientoEncargado(Integer idMantenimientoEncargado) {
        this.idMantenimientoEncargado = idMantenimientoEncargado;
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
        return "sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.MantenimientoEncargado[ idMantenimientoEncargado=" + idMantenimientoEncargado + " ]";
    }
    
}
