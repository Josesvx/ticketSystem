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
@Table(name = "directorio", catalog = "ticketdb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directorio.findAll", query = "SELECT d FROM Directorio d")
    , @NamedQuery(name = "Directorio.findByIdDirectorio", query = "SELECT d FROM Directorio d WHERE d.idDirectorio = :idDirectorio")
    , @NamedQuery(name = "Directorio.findByNombre1", query = "SELECT d FROM Directorio d WHERE d.nombre1 = :nombre1")
    , @NamedQuery(name = "Directorio.findByNombre2", query = "SELECT d FROM Directorio d WHERE d.nombre2 = :nombre2")
    , @NamedQuery(name = "Directorio.findByApellido1", query = "SELECT d FROM Directorio d WHERE d.apellido1 = :apellido1")
    , @NamedQuery(name = "Directorio.findByApellido2", query = "SELECT d FROM Directorio d WHERE d.apellido2 = :apellido2")
    , @NamedQuery(name = "Directorio.findByCorreo", query = "SELECT d FROM Directorio d WHERE d.correo = :correo")
    , @NamedQuery(name = "Directorio.findByContrasenia", query = "SELECT d FROM Directorio d WHERE d.contrasenia = :contrasenia")
    , @NamedQuery(name = "Directorio.findByUsuario", query = "SELECT d FROM Directorio d WHERE d.usuario = :usuario")})
public class Directorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_directorio")
    private Integer idDirectorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre1")
    private String nombre1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre2")
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellido1")
    private String apellido1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellido2")
    private String apellido2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @JoinColumn(name = "id_departemento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private Departamento idDepartemento;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirectorio")
    private List<Encargado> encargadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirectorio")
    private List<Solicitud> solicitudList;

    public Directorio() {
    }

    public Directorio(Integer idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public Directorio(Integer idDirectorio, String nombre1, String nombre2, String apellido1, String apellido2, String correo, String contrasenia, String usuario) {
        this.idDirectorio = idDirectorio;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.usuario = usuario;
    }

    public Integer getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(Integer idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Departamento getIdDepartemento() {
        return idDepartemento;
    }

    public void setIdDepartemento(Departamento idDepartemento) {
        this.idDepartemento = idDepartemento;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @XmlTransient
    public List<Encargado> getEncargadoList() {
        return encargadoList;
    }

    public void setEncargadoList(List<Encargado> encargadoList) {
        this.encargadoList = encargadoList;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirectorio != null ? idDirectorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Directorio)) {
            return false;
        }
        Directorio other = (Directorio) object;
        if ((this.idDirectorio == null && other.idDirectorio != null) || (this.idDirectorio != null && !this.idDirectorio.equals(other.idDirectorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio[ idDirectorio=" + idDirectorio + " ]";
    }
    
}
