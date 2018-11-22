/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
public class CookieInstance {

    private Cookie galleta;
    private Directorio directorio = new Directorio();
    private boolean control = false;
    private final String user = "usuario";
    private Cookie[] galletas;

    public CookieInstance() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        galletas = request.getCookies();
        if (galletas != null && galletas.length > 0) {
            for (Cookie cookie : galletas) {
                if (cookie.getName().equals(user)) {
                    galleta = cookie;
                    control = true;
                    break;
                }
            }
        }

    }

    public void CrearCookie(Directorio directory) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        galletas = request.getCookies();
        if (galletas != null && galletas.length > 0) {
            for (Cookie cookie : galletas) {
                if (cookie.getName().equals(user)) {
                    galleta = cookie;
                    control = true;
                    break;
                }
            }
        }
        if (control == true) {
            galleta.setValue(directory.getIdDirectorio().toString());
        } else {
            galleta = new Cookie(user, directory.getIdDirectorio().toString());
            galleta.setPath(request.getContextPath());
        }
        response.addCookie(galleta);
    }

    public int UsuarioId() {
        int id;
        if (control == true) {
            id = Integer.parseInt(galleta.getValue());
            return id;
        } else {
            return 0;
        }

    }

    public void cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        try {
            galletas = request.getCookies();
            if (galleta != null && galletas.length > 0) {
                for (Cookie cookies : galletas) {
                    if (cookies.getName().equals(user)) {
                        cookies.setValue("");
                        cookies.setPath("/ticketSystem-1.0-SNAPSHOT");
                        cookies.setMaxAge(0);
                        response.addCookie(cookies);
                        control = true;
                        break;
                    }
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            }
        } catch (IOException ex) {
            Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ComprobarLogin() {
        if (control == true) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ComprobarLoginPricipal() {
        if (control == false) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CookieInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Directorio getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Directorio directorio) {
        this.directorio = directorio;
    }

}
