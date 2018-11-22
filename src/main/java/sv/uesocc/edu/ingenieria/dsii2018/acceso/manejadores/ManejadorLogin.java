/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
@Named
@ViewScoped
public class ManejadorLogin implements Serializable {

    @EJB
    private DirectorioFacadeLocal Dfl;
    private Directorio directorio, Usuario, direc;
    private String redireccionar = null, nombreUsuario, rol;
    private CookieInstance oreo;
    private int id, id2, idg;

    @PostConstruct
    public void init() {
        directorio = new Directorio();
        oreo = new CookieInstance();

    }

    public Directorio getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Directorio directorio) {
        this.directorio = directorio;
    }

    public String autenticacion() {
        Directorio user;
        try {
            user = Dfl.autenticar(directorio);
            if (user != null) {
                oreo.CrearCookie(user);
                redireccionar = "principal.jsf?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "aviso:", "usuario o contraseña incorrectos"));
            }

        } catch (Exception ex) {
            throw ex;
        }
        return redireccionar;
    }

    public String nombreiniciar() {
        id2 = oreo.UsuarioId();
        Usuario = Dfl.find(id2);
        nombreUsuario = Usuario.getUsuario();
        return nombreUsuario;

    }

    public String nombreRol() {
        id2 = oreo.UsuarioId();
        Usuario = Dfl.find(id2);
        rol = Usuario.getIdRol().getNombre();
        return rol;

    }

    public void login() {
        oreo.ComprobarLogin();
    }

    public void loginPrincipal() {
        oreo.ComprobarLoginPricipal();
    }

    public void loginGerente() {
        id = oreo.UsuarioId();
        if (id > 0) {
            directorio = Dfl.find(id);
            if (!(directorio.getIdRol().getIdRol() == 4)) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loginTecnico() {
        id = oreo.UsuarioId();
        if (id > 0) {
            directorio = Dfl.find(id);
            if (!(directorio.getIdRol().getIdRol() == 2 || directorio.getIdRol().getIdRol() == 4)) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loginJefe() {
        id = oreo.UsuarioId();
        if (id > 0) {
            directorio = Dfl.find(id);
            if (!(directorio.getIdRol().getIdRol() == 3 || directorio.getIdRol().getIdRol() == 4)) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean usuariogerente() {
        idg = oreo.UsuarioId();
        direc = Dfl.find(idg);
        if ((direc.getIdRol().getIdRol() == 4) || direc.getIdRol().getIdRol() == 4) {
            return true;

        } else {
            return false;
        }
    }

    public boolean usuariojefedepto() {
        idg = oreo.UsuarioId();
        direc = Dfl.find(idg);
        if ((direc.getIdRol().getIdRol() == 3 || direc.getIdRol().getIdRol() == 4)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean usuariotecnico() {
        idg = oreo.UsuarioId();
        direc = Dfl.find(idg);
        if ((direc.getIdRol().getIdRol() == 2 || direc.getIdRol().getIdRol() == 4)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean GerenteGeneral() {
        idg = oreo.UsuarioId();
        direc = Dfl.find(idg);
        if ((direc.getIdRol().getIdRol() == 4)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean GerenteGeneralTabla() {
        idg = oreo.UsuarioId();
        direc = Dfl.find(idg);
        if ((direc.getIdRol().getIdRol() == 4)) {
            return false;

        } else {
            return true;
        }
    }
    public void CerrarSesion(){
        oreo.cerrarSesion();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Directorio getDirec() {
        return direc;
    }

    public void setDirec(Directorio direc) {
        this.direc = direc;
    }

    public int getIdg() {
        return idg;
    }

    public void setIdg(int idg) {
        this.idg = idg;
    }

}
