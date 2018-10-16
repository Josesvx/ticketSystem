/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "DescripcionMantenimiento.findByDescripcionSolucion", query = "SELECT d FROM DescripcionMantenimiento d WHERE d.descripcionSolucion = :descripcionSolucion")})
public class DescripcionMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descripcion_mantenimiento")
    private Integer idDescripcionMantenimiento;
    @Size(max = 500)
    @Column(name = "descripcion_problema")
    private String descripcionProblema;
    @Size(max = 500)
    @Column(name = "descripcion_solucion")
    private String descripcionSolucion;
    @OneToMany(mappedBy = "idDescripcionMantenimiento")
    private List<MantenimientoEncargado> mantenimientoEncargadoList;

    public DescripcionMantenimiento() {
    }

    public DescripcionMantenimiento(Integer idDescripcionMantenimiento) {
        this.idDescripcionMantenimiento = idDescripcionMantenimiento;
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
        return "sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento[ idDescripcionMantenimiento=" + idDescripcionMantenimiento + " ]";
    }
    
}
